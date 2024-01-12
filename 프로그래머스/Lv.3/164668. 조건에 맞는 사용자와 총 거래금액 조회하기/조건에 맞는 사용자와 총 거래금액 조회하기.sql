# USED_GOODS_BOARD와 USED_GOODS_USER 테이블에서 완료된 중고 거래의 총금액이 70만 원 이상인 사람의 회원 ID, 닉네임, 총거래금액을 조회하는 SQL문을 작성해주세요. 결과는 총거래금액을 기준으로 오름차순 정렬해주세요.
-- 코드를 입력하세요
# SELECT user_id, nickname, price AS TOTAL_SALES
#  FROM USED_GOODS_USER (SELECT price
#                 FROM USED_GOODS_BOARD
#                 GROUP BY user_id)
# WHERE price >= '700000'
# ORDER BY TOTAL_SALES ASC;

SELECT B.USER_ID, B.NICKNAME, SUM(A.PRICE) AS TOTAL_SALES 
FROM USED_GOODS_BOARD A
JOIN USED_GOODS_USER B ON A.WRITER_ID = B.USER_ID
WHERE A.STATUS = 'DONE'
GROUP BY B.USER_ID
HAVING TOTAL_SALES >= 700000
ORDER BY TOTAL_SALES
