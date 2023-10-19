document.addEventListener('DOMContentLoaded', function() {
    const itemCon = document.getElementById('likecard');
    const loadMoreButton = document.getElementById('load-more');
    const itemsPerPage = 3;
    let currentItems = itemsPerPage;

    loadMoreButton.addEventListener('click', function() {
        const items = itemCon.querySelectorAll('.card');
        for (let i = currentItems; i < currentItems + itemsPerPage; i++) {
            if (items[i]) {
                items[i].style.display = 'block';
            }
        }
        currentItems += itemsPerPage;

        if (currentItems >= items.length) {
            loadMoreButton.style.display = 'none';
        }
    });

    // 초기에는 첫 3개의 항목만 보여주고 나머지 숨김
    const items = itemCon.querySelectorAll('.card');
    for (let i = itemsPerPage; i < items.length; i++) {
        items[i].style.display = 'none';
    }

    // 모든 항목이 보여질 때 더보기 버튼 숨김
    if (items.length <= itemsPerPage) {
        loadMoreButton.style.display = 'none';
    }
});
