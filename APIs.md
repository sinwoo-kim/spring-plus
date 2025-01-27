## Delivery App

### <span style="color: red;">**GET**</span> 일정 검색
```
/todos/search
```
**Request**
```
{
"title":"오늘",
"startDate":"2025-01-27",
"endDate":"2025-01-27",
"nickName":"김"
}
```
**Response**
```
{
    "content": [
        {
            "title": "오늘 할 일",
            "managerList": 1,
            "commentList": 0
        }
    ],
    "page": {
        "size": 10,
        "number": 0,
        "totalElements": 1,
        "totalPages": 1
    }
}
```
