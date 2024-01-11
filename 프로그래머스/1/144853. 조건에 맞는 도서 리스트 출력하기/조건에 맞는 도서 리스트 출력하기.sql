SELECT book_id, DATE_FORMAT(published_date, '%Y-%m-%d')
FROM BOOK
WHERE DATE_FORMAT(published_date, '%Y') = '2021'
AND category = '인문'
ORDER BY PUBLISHED_DATE ASC;