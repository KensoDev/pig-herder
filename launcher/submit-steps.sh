cluster_id=$1
date_string=$2
after_action=$3
aws emr add-steps --cluster-id $cluster_id --steps "Type=PIG,Name=\"Pig Program\",ActionOnFailure=$after_action,Args=[-f,s3://recalc-poi/script.pig,-p,lib_folder=/home/hadoop/pig/lib/,-p,input_location=s3://recalc-poi,-p,output_location=s3://recalc-poi,-p,jar_location=s3://recalc-poi,-p,output_file_name=output-$date_string]"

