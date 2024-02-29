#!/bin/bash

# shellcheck disable=SC2046
echo $(awslocal s3api create-bucket --bucket $AWS_BUCKET)
awslocal s3api create-bucket --bucket $AWS_BUCKET
echo "CREATED BUCKET $AWS_BUCKET"
#echo $(awslocal s3api create-bucket --bucket fallback-permanent-bucket)

#echo $(awslocal s3api create-bucket --bucket localstack-s3-bucket)
#awslocal s3api create-bucket --bucket localstack-s3-bucket
#echo $(awslocal s3api create-bucket --bucket fallback-permanent-bucket)