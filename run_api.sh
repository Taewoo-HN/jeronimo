. /root/anaconda3/etc/profile.d/conda.sh
cd /usr/local/etc/ModelAPI || exit 1
conda activate tensorflow_cuda
uvicorn main:app --host=0.0.0.0