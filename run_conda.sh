# Conda 환경 활성화
. /root/anaconda3/etc/profile.d/conda.sh
conda activate ml-dev

systemctl start mariadb
systemctl start kafka-ui

airflow db init &           # Airflow DB 초기화
airflow celery worker &     # Airflow Celery Worker
airflow scheduler &         # Airflow Scheduler
airflow webserver &         # Airflow Webserver

# 모든 백그라운드 작업이 종료될 때까지 기다림
wait