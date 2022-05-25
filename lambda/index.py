# Write batch messages to queue
import csv
import boto3
import random
import json
import uuid

sqs_client = boto3.client('sqs')
s3 = boto3.resource('s3')

# Update this dummy URL
processing_queue_url = "https://sqs.us-east-2.amazonaws.com/961274951017/new-relic-import-cep"

def handler(event, context):
    try:
        # if 'Records' in event:
        bucket_name = 'tqi-new-relic'
        key = 'cep1000.csv'
        bucket = s3.Bucket(bucket_name)
        obj = bucket.Object(key=key)

        # get the object
        response = obj.get()['Body'].read().decode('utf-8').split('\n')
        resp = list(response)
        if resp[-1] == '':
            #removing header metadata and extra newline
            total_records = len(resp) - 2
        else:
            #removing header metadata
            total_records = len(resp) - 1
        print("total record count is :", total_records)

        batch_size = 0
        record_count = 0
        messages = []

        # Write to SQS
        for row in csv.DictReader(response):
            record_count += 1
            record = {}
            for k,v in row.items():
                record[k] = v

            unique_id = uuid.uuid4().hex

            batch_size += 1
            messages.append(
            {
                'Id': unique_id,
                'MessageBody': json.dumps(record)
            })

            if (batch_size == 10):
                batch_size = 0
                try:
                    response = sqs_client.send_message_batch(
                        QueueUrl = processing_queue_url,
                        Entries = messages
                    )
                    # print("response:", response)
                    if 'Failed' in response:
                        print('failed_count:', len(response['Failed']))
                except Exception as e:
                    print("error:",e)
                messages = []

            # Handling last batch
            if (record_count == total_records):
                print("batch size is :", batch_size)
                batch_size = 0
                try:
                    response = sqs_client.send_message_batch(
                        QueueUrl = processing_queue_url,
                        Entries = messages
                    )
                    # print("response:", response)
                    if 'Failed' in response:
                        print('failed count is :', len(response['Failed']))
                except Exception as e:
                    print("error:",e)
                messages = []

        print('record count is :', record_count)

    except Exception as e:
        return e