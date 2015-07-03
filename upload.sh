echo "Uploading Bootstrap actions"...
s3cmd put bootstrap.sh s3://$BUCKET_NAME/ >> /dev/null
echo "Uploading Pig Script"...
s3cmd put script.pig s3://$BUCKET_NAME/ >> /dev/null
echo "Uploading Jars..."
s3cmd put jars/pig-herder.jar s3://$BUCKET_NAME/ >> /dev/null
echo "Finished!"