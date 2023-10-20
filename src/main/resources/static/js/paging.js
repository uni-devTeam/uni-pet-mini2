
// 값 받아오기
document.addEventListener("DOMContentLoaded", function() {
    var itemsDiv = document.getElementById('items');
    var paginationDiv = document.getElementById('pagination');
    var page = 1;
    var limit = 10; // 페이지당 아이템 수, 필요한 제한 값을 설정합니다

    var xhr = new XMLHttpRequest();
    xhr.onload = function() {
        if (xhr.status === 200) {
            console.log(xhr.responseText);
            var jsonData = JSON.parse(xhr.responseText);
            var len = jsonData.length;

            for(let i = 0; i < len; i++) {
                var board_no = jsonData[i].board_no;
                var title = jsonData[i].title;
                var posting_date = jsonData[i].posting_date;

                loadItems(board_no, title, posting_date, len);
            }
        }
    };
    var url = '/items?limit=' + limit + '&page=' + page;
    xhr.open('POST', url, true);
    xhr.send();

// 게시글 페이지당 가져오기
    var i = 0;
    function loadItems(board_no, title, posting_date, len) {

        var itemHTML = '<tr>' +
            '<input type="hidden" value="' + board_no + '">' +
                '<td class="tb_num">' + board_no + '</td>' +
                '<td class="tb_title">' +
                    '<a href="/board/content?board_no=' + board_no + '">' + title + '</a>' +
                '</td>' +
                '<td>' + posting_date + '</td>' +
            '</tr>';

        itemsDiv.innerHTML += itemHTML;
        i = i + 1;
    }
});

//fuction loadPagination() {
//    var xhr = new XMLHttpRequest();
//}

//loadItems(page);
//loadPagination();