# Python 이미지 가져오기
FROM python:3.10

# 작업 폴더 설정
WORKDIR /app

# 라이브러리 먼저 복사
COPY requirements.txt .

# 라이브러리 설치
RUN pip install -r requirements.txt

# 코드 복사
COPY . .

# 실행
CMD ["python", "app.py"]