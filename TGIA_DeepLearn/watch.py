import time
import subprocess
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler

class MyHandler(FileSystemEventHandler):
    def on_created(self, event):
        # flag = False
        if event.is_directory:
            return None
        elif event.event_type == 'created':
            # flag = True
            # 파일이 생성되었을 때 실행할 코드 작성
            print(f"File created: {event.src_path}")
            # command = 'python detect_spring.py --weights yolov5s.pt --source "' + event.src_path + '"'
            command = 'python detect_spring.py --weights best5_24.pt --source "' + event.src_path + '"' + ' --conf 0.2'

            result = subprocess.run(command, stdout=subprocess.PIPE, shell=True)
            output = result.stdout.decode('utf-8')  # 결과값 디코딩
            print("Command output:", output)

if __name__ == "__main__":
    event_handler = MyHandler()
    observer = Observer()
    # 감시할 디렉토리 경로를 지정합니다.
    path = "/Users/son/desktop/upload"
    observer.schedule(event_handler, path, recursive=True)
    observer.start()
    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        observer.stop()
    observer.join()