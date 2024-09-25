#!/bin/bash
# Conda 환경 활성화
. /root/anaconda3/etc/profile.d/conda.sh
conda activate ml-dev

systemctl start kafka

nohup airflow celery worker
nohup airflow scheduler
nohup airflow webserver



