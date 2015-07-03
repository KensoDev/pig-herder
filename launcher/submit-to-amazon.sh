date_string=`date -v-1d +%F`

echo "Starting process on: $date_string"

cluster_id=`aws emr create-cluster --name "$CLUSTER_NAME-$date_string" \
  --log-uri s3://$BUCKET_NAME/logs/ \
  --ami-version 3.8.0 \
  --applications Name=Hue Name=Hive Name=Pig \
  --use-default-roles --ec2-attributes KeyName=$KEY_NAME \
  --instance-type m3.xlarge --instance-count 3 \
  --bootstrap-action Path=s3://$BUCKET_NAME/bootstrap.sh | awk '$1=$1' ORS='' | grep ClusterId | awk '{ print $2 }' | sed s/\"//g | sed s/}//g`

echo "Cluster Created: $cluster_id"

sh submit-steps.sh $cluster_id $date_string CONTINUE