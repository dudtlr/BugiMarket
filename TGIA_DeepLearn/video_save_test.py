import cv2
import time

# 웹캠 영상 캡쳐 객체 생성
capture = cv2.VideoCapture(0)

# 동영상 프레임 크기, FPS 정보 가져오기
frame_size = (int(capture.get(cv2.CAP_PROP_FRAME_WIDTH)),
              int(capture.get(cv2.CAP_PROP_FRAME_HEIGHT)))
fps = int(capture.get(cv2.CAP_PROP_FPS))

# 저장할 동영상 파일 열기
fourcc = cv2.VideoWriter_fourcc(*'mp4v')
out = None
start_time = None

while True:
    # 웹캠 영상 읽어오기
    ret, frame = capture.read()

    # 웹캠이 정상적으로 작동하면 프레임을 처리합니다.
    if ret:
        # 현재 시간 가져오기
        now = time.time()

        # 저장 버튼이 눌리면 동영상 저장을 시작합니다.
        key = cv2.waitKey(1) & 0xFF
        if key == ord('s'):
            # 이전 동영상이 저장 중이면 종료합니다.
            if out is not None:
                out.release()

            # 새로운 동영상 파일을 엽니다.
            filename = f'output_{now}.mp4'
            out = cv2.VideoWriter(filename, fourcc, fps, frame_size)
            start_time = now

        # 동영상 저장 중이면 프레임을 저장합니다.
        if out is not None:
            out.write(frame)

            # 10초 단위로 동영상 저장합니다.
            if now - start_time > 10:
                out.release()
                out = None
                start_time = None

        # 프레임을 화면에 보여줍니다.
        cv2.imshow('frame', frame)

    # 웹캠이 정상적으로 작동하지 않을 때는 종료합니다.
    else:
        break

    # 종료 버튼이 눌리면 종료합니다.
    if key == ord('q'):
        break

# 저장 중이던 동영상 파일을 닫습니다.
if out is not None:
    out.release()

# 웹캠 캡쳐 객체를 닫습니다.
capture.release()

# OpenCV 윈도우를 닫습니다.
cv2.destroyAllWindows()