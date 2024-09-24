# Conda 환경 활성화
. /root/anaconda3/etc/profile.d/conda.sh
conda activate ml-dev

systemctl start kafka

airflow celery worker
airflow scheduler
airflow webserver



