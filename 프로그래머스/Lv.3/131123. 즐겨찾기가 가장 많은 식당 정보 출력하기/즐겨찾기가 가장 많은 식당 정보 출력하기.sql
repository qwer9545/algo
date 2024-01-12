# REST_INFO 테이블에서 음식종류별로 즐겨찾기수가 가장 많은 식당의 음식 종류, ID, 식당 이름, 즐겨찾기수를 조회하는 SQL문을 작성해주세요. 이때 결과는 음식 종류를 기준으로 내림차순 정렬해주세요.
-- 코드를 입력하세요
SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
  FROM REST_INFO
 WHERE (FOOD_TYPE, FAVORITES) IN (SELECT FOOD_TYPE, MAX(FAVORITES)
                                  FROM REST_INFO
                                  GROUP BY FOOD_TYPE)
ORDER BY FOOD_TYPE DESC;

/*
FOOD_TYPE	REST_ID	REST_NAME	MAX(FAVORITES)
한식	00001	은돼지식당	734
중식	00015	만정	20
일식	00002	하이가쯔네	230
양식	00003	따띠따띠뜨	102
분식	00008	애플우스	151
*/