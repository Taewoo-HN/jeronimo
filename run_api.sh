. /root/miniconda3/etc/profile.d/conda.sh
cd /root/serving/ModelAPI || exit 1
conda activate cpu_ai
uvicorn main:app --host=0.0.0.0