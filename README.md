# 📖 부기마켓 - 우리 학교 학생들을 위한 중고거래 앱 


<img width="1920" alt="bugi" src="https://github.com/user-attachments/assets/66ac8839-dfab-4b1d-870c-886393918cc6">
<br>

## 📚 목차
- 프로젝트 소개
- 팀원 구성 및 역할
- 개발 환경
- 프로젝트 주요 기능
- 프로젝트 구조
- ERD 설계도
- 기능 별 화면 및 소개


<br>

## 📝 프로젝트 소개
기존의 중고 거래 어플을 이용해본 경험이 있는 사람들은 본인이 구매하려는 물건이 하자가 있는 불량품은 아닐지, 아니면 사기를 당하여 금전적인 손해를 본다든가 직거래시 폭행이나 협박 등의 범죄에 노출되지 않을까 걱정한 경험이 있다고 답하였고, 이러한 불안과 범죄로부터 한성대학교 학생들의 자유롭고 안전한 중고 거래 환경을 조성하고자 기존에 형성되어있는 중고 거래 어플을 한성대학교에 맞게 한성대학교의 마스코트 캐릭터인 상상부기와 디자인을 접목시킨 “부기마켓' 이라는 어플을 만드는 프로젝트를 진행했습니다.

<br>

## 👩‍👩‍👧‍👦 팀원 및 담당 파트
<div sytle="overflow:hidden;">
<table>
   <tr>
      <td colspan="2" align="center"><strong>Front-End Developer</strong></td>
      <td colspan="2" align="center"><strong>Back-End Developer</strong></td>
   </tr>
  <tr>
     <td align="center">
        <a href="https://github.com/geonheeMin"><img src="https://avatars.githubusercontent.com/u/130971355?v=4" width="150px" alt="민건희"/><br/><sub><b>민건희(팀장)</b></sub></a>
     </td>
    <td align="center">
        <a href="https://github.com/BraveKey"><img src="https://avatars.githubusercontent.com/u/125978827?v=4" width="150px;" alt="조용기"/><br/><sub><b>조용기</b></sub></a>
    </td>
     <td align="center">
        <a href="https://github.com/MKSonny"><img src="https://avatars.githubusercontent.com/u/116620246?v=4" width="150px" alt="손민규"/><br/><sub><b>손민규</b></sub></a>
     </td>
     <td align="center">
        <a href="https://github.com/dudtlr"><img src="https://avatars.githubusercontent.com/u/95243456?v=4" width="150px" alt="전영식"/><br/><sub><b>전영식</b></sub></a>
     </td>
  <tr>
</table>
</div>

> 민건희 : React Native 사용 모바일 애플리케이션 공동 개발 / 모바일 애플리케이션 디자인 / 팀장 <br>
> 조용기 : React Native 사용 모바일 애플리케이션 공동 개발 / React 사용 관리자 통계 조회 웹사이트 개발 <br>
> 손민규 : Spring Boot 사용 백엔드 애플리케이션 공동 개발 / 채팅 및 Yolov5 사용 딥러닝 애플리케이션 개발 <br>
> 전영식 : Spring Boot 사용 백엔드 애플리케이션 공동 개발 / 게시글 검색 및 카카오페이 간편결제 기능 개발 <br>
<br>

## 📌 개발 환경
<div>
    <table>
        <tr>
            <td colspan="2" align="center">
                Language
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/Typescript-3178c6?style=for-the-badge&logo=typescript&logoColor=white">
                <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=openjdk&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Library & Framework
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/React_Native-61dafb?style=for-the-badge&logo=React&logoColor=black">
                <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                API
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/Kakao Pay API-FFCD00?style=for-the-badge&logo=kakao&logoColor=black"> 
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Server
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/amazon ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> 
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Database
            </td>
            <td colspan="4">
              <img src="https://img.shields.io/badge/mysql-4479a1?style=for-the-badge&logo=mySQL&logoColor=white">
              <img src="https://img.shields.io/badge/Elasticsearch-005571?style=for-the-badge&logo=elasticsearch&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Tool
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/intellij idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
                <img src="https://img.shields.io/badge/visual studio code-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                etc.
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white">
                <img src="https://img.shields.io/badge/jira-0a75dd?style=for-the-badge&logo=jira&logoColor=white">
            </td>
        </tr>
    </table>
</div>
<br>

## 🚀 프로젝트 주요 기능

|      기능       |                          설명                           |                                  비고                                   |
|:-------------:|:-----------------------------------------------------:|:---------------------------------------------------------------------:|
| 게시글 <br> 필터링 |                 (시작 화면)(트랙,학부,단과대,전체) 조건에 맞는 게시글을 보여준다.                   | QueryDsl을 사용하여 구현                     |
| 게시글 <br>필터링 |              (검색 화면)(키워드,거래장소,학부,카테고리,정렬(최신,좋아요,조회)) 조건에 맞는 게시글을 보여준다.              | QueryDsl을 사용하여 구현.  <br> 검색된 키워드의 횟수를 활용하여 인기검색어 기능 구현.  |
|    장바구니    |               해당 상품이 장바구니에 담김.                |  좋아요 수 증가.|
|     게시글 <br>상세<br> 페이지      |      게시글에 대한 상세 정보를 확인할 수 있다. <br> 조회 시 조회 수 증가. (중복 방지)   |  게시글 상세 페이지에서는 여러 장의 이미지를 슬라이드로 확인하고, <br> 특정 카테고리를 선택하면 해당 카테고리의 다른 게시글들을 확인할 수 있다.   |
|   채 팅    |                  문의하기 버튼을 통해 판매자와 채팅을 할 수 있다.                |               게시글 상세 페이지에서 현재 진행 중인 채팅 목록과 읽지 않은 채팅 개수를 확인할 수 있다.<br> 해당 채팅방을 터치하면 채팅 내역과 읽음 여부를 확인.        |
|    결 제     |                카카오 api를 통한 결제를 할 수 있다.                |                           채팅방에서 송금하기를 선택하여 거래 예약, 카카오페이 결제 프로세스 완료 후 결과 url을 인식하여 송금을 완료했다는 채팅을 자동으로 전송.                          |
|   게시글<br> 작성    |                 게시글을 작성할 수 있다.                  |                              게시글 이미지, 제목, 품목명, 카테고리, 가격, 내용, 장소, 트랙(학과)을 설정 할 수 있다. <br> 이미지를 선택하면 yolov5와 roboflow로 학습시킨 데이터를 통해 카테고리가 자동으로 추천된다.                           |
|     게시글 수정, 삭제     |     게시글을 수정 또는 삭제 할 수 있다.     |             판매 상품의 상세 정보를 확인하고<br/>상품에 대한 리뷰를 확인 및 작성 가능.             |
|     프로필 <br>정보      |                프로필 정보를 확인할 수 있다.                 |                         프로필 이미지, 매너온도, 소속 트랙(학과), 판매내역, 구매내역, 받은 매너 평가(리뷰) 확인 가능.                       |
|     판매<br> 내역     |         판매한 상품들의 내역을 보여준다.         |         판매가 완료가 되면 검색에서 해당 게시글이 보이지 않음.           |
|     구매 <br>내역     | 구매한 상품들의 내역을 보여준다.    |                    구매 이후 거래 리뷰를 남길 수 있음.                |
|     리뷰작성      |                구매한 상품에 대한 리뷰 작성 가능  .                 |                         받은 후기가 여러개일 경우, 최신순으로 3개까지 표시된다.|
|     매너온도     | 판매자의 매너온도를 확인할 수 있다. |                    평가를 바탕으로 매너온도가 증가되거나 감소한다.                    |
|     관리자 <br> 웹페이지     |         앱의 전반적인 정보를 보여준다.        |        보내줄 정보가 많아 HashMap을 통해 알기 쉬운 형태로 프론트로 전달하였음.          |
<br>




<br>

## 🔨 프로젝트 구조

<img width="1206" alt="image" src="https://github.com/user-attachments/assets/bee3c82e-997b-4d28-bd7c-d15b320bf6f1" />

<br>

## 🔨 ERD 설계도

<img width="695" alt="image" src="https://github.com/user-attachments/assets/3429f391-534a-455f-a5c5-10a52c6fb2ec" />


<br>


## ️ ️🌟 기능 별 화면 및 소개

### ✔ 게시글 필터링
-  (시작 화면) (트랙,학부,단과대,전체)  조건에 맞는 게시글을 보여준다.

<br>

<img width="1192" alt="image" src="https://github.com/user-attachments/assets/a52e2930-9d3d-4707-9ffe-7d6f43fad0c0" />


<br>


<br>

-  (검색 화면) (키워드,거래장소,학부,카테고리,정렬(최신,좋아요,조회)) 조건에 맞는 게시글을 보여준다.
-  키워드 검색 시 게시글 제목에 키워드가 포함되면 조건에 포함된다.
<br>

<img width="1198" alt="image" src="https://github.com/user-attachments/assets/49d2be2a-e47d-4369-892b-1864826ae8a1" />

<img width="1202" alt="image" src="https://github.com/user-attachments/assets/d87b2a13-a8b6-452a-baa0-870a988f6591" />

<img width="1201" alt="image" src="https://github.com/user-attachments/assets/12d26a41-9597-4b12-8c62-f2b41100baf7" />
<img width="1208" alt="image" src="https://github.com/user-attachments/assets/2e71e461-29ff-48ee-bfac-2fe3c132c1cc" />

<img width="1211" alt="image" src="https://github.com/user-attachments/assets/eae35928-5311-406d-8e24-bfbb6fb32873" />




<br>
<br>



### ✔ 장바구니 
-   해당 상품이 장바구니에 담김
<br>

<img width="1205" alt="image" src="https://github.com/user-attachments/assets/8c12f680-cc89-4f62-b535-cc84c36e5203" />



<br>


### ✔ 게시글 상세 페이지
- 게시물 선택하면 상세 페이지로 이동하고, 스와이프로 장소 확인 가능. 이미지는 슬라이드로 볼 수 있고, 카테고리 터치 시 해당 목록으로 이동. 스크롤로 거래 정보 및 판매자 상품 확인하며, 동일 카테고리의 다른 게시물 표시. 버튼으로 동일 카테고리 목록 확인 가능.
  
<br>

<img width="1215" alt="image" src="https://github.com/user-attachments/assets/db4aa408-caf6-4050-8de5-4db28fb262e0" />

<img width="1212" alt="image" src="https://github.com/user-attachments/assets/dc878f29-06e5-4b21-9704-c4de8ee65cef" />


<br>


### ✔ 게시글 채팅
- 게시물에서 채팅 목록을 터치하면 해당 게시물의 채팅 확인. 채팅방 터치로 채팅 내용 확인 가능. 구매자로부터 채팅이 오면 대화 후 송금 요청 가능. 결제는 카카오페이 프로세스와 동일.

<br>

<img width="1214" alt="image" src="https://github.com/user-attachments/assets/f06f2389-f7b0-4c65-854b-b770de242bb3" />

<img width="1213" alt="image" src="https://github.com/user-attachments/assets/da5832f9-92ad-467b-a3da-e22bb9c6bfa5" />


<br>

### ✔ 결제하기
- 프론트에서 게시글 정보를 스프링 서버로 전달 후, 카카오페이 서버로 데이터 전송. 올바른 정보라면 결제 화면을 열 수 있는 URL을 받아 프론트로 전달. 결제 후 스프링 서버를 거쳐 최종 결제 승인까지 진행되며, 동시에 앱에서의 결제 과정도 처리하여 결제 완료창을 보여줌.

<br>

<img width="1199" alt="image" src="https://github.com/user-attachments/assets/1d8526d3-4fd4-4679-a6ba-4001d562f633" />

<img width="1199" alt="image" src="https://github.com/user-attachments/assets/cb23bc38-2986-43a2-999e-1573589a965a" />
<img width="1211" alt="image" src="https://github.com/user-attachments/assets/dffbc50b-0f95-4a13-b94c-1a21830d58b7" />



<br>

### ✔ 게시글 작성
- 게시물 목록에서 연필 아이콘을 터치하여 게시글 정보 및 이미지, 제목, 품목명, 카테고리, 가격, 내용, 장소, 트랙 설정 가능. 카메라 아이콘을 터치하여 사진 촬영 또는 앨범에서 선택 후 이미지 추가 가능. 제목, 가격, 내용 입력 후 거래 희망 장소 및 트랙 선택 가능.

<br>

<img width="1196" alt="image" src="https://github.com/user-attachments/assets/fefb3e09-bee2-494d-bf5d-21fd26694b63" />




### ✔ 게시글 수정, 삭제
- 게시글 수정, 삭제 가능

<br>

<img width="1224" alt="image" src="https://github.com/user-attachments/assets/63aaed77-6e2a-4654-a3bd-b9592305b445" />



<br>

### ✔ 프로필 정보
- 프로필 탭을 누르면 내 프로필 화면으로 이동하며, 전반적인 정보 확인 및 트랙 설정, 프로필 변경 기능 제공됩니다. 판매 목록, 구매 목록, 찜 목록 등으로 나의 게시글 현황 확인 가능합니다. 트랙 변경을 원할 시 해당 트랙을 선택하여 등록하고 변경 버튼을 누르면 완료됩니다. 이 정보는 게시글 필터링 및 상대방이 내 프로필을 볼 때 활용됩니다.


<img width="1217" alt="image" src="https://github.com/user-attachments/assets/7a6028ce-fe27-43cf-b5ac-45574bbc925a" />
<img width="1217" alt="image" src="https://github.com/user-attachments/assets/3cb41d3c-78be-4e8b-b78b-4061bf218ce5" />






<br>

### ✔ 판매내역
- 판매한 상품들의 내역을 보여준다.

<br>

<img width="1211" alt="image" src="https://github.com/user-attachments/assets/4cd6f1d5-0974-4a10-beb9-3d89794f8a3f" />





### ✔ 구매내역
- 구매한 상품들의 내역을 보여준다.

<br>

<img width="1203" alt="image" src="https://github.com/user-attachments/assets/5f2c6857-a1d6-4b2d-9a6c-a75defffc4c7" />


<br>

### ✔ 구매 리뷰
-  구매한 상품에 대한 리뷰 작성 가능.

<br>

<img width="1201" alt="image" src="https://github.com/user-attachments/assets/d61a26dc-dac5-4e9c-9419-90a15c66439a" />
<img width="1204" alt="image" src="https://github.com/user-attachments/assets/62496cb8-3a82-46b2-9934-aaa71d2627fd" />




<br>


### ✔ 매너온도
- 매너 학점을 터치하면 매너 프로필로 이동하고, 판매 상품 버튼으로 현재 유저의 상품 리스트를 확인할 수 있습니다. 판매자 정보를 누르면 매너 프로필을 확인할 수 있습니다.

<br>

<img width="1198" alt="image" src="https://github.com/user-attachments/assets/3b1283c8-3d6f-4c46-a862-8803c84669d1" />




### ✔ 관리자 웹 페이지
- 앱의 전반적인 정보를 보여준다.
<br>

<img width="1224" alt="image" src="https://github.com/user-attachments/assets/0c292f5c-2ee6-4de0-96a6-1190ad499d91" />


<br>



