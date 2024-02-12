#!/bin/bash
echo $(awslocal s3api create-bucket --bucket localstack-s3-bucket)
awslocal s3api create-bucket --bucket localstack-s3-bucket
#echo $(awslocal s3api create-bucket --bucket fallback-permanent-bucket)