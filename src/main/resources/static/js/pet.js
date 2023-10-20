    // 파일 입력 필드가 변경될 때 호출되는 함수
    document.getElementById('attach').addEventListener('change', function() {
        const fileInput = this;
        const preview = document.getElementById('preview');

        if (fileInput.files && fileInput.files[0]) {
            const reader = new FileReader();

            reader.onload = function (e) {
                // 이미지 미리보기 표시
                preview.src = e.target.result;
            };

            // 파일 읽기 시작
            reader.readAsDataURL(fileInput.files[0]);
        }
    });

    // 생일 선택 달력
    flatpickr("#petBirthPicker", {
        dateFormat: "Y-m-d", // 원하는 날짜 형식으로 설정 (예: 년-월-일)
        enableTime: false, // 시간을 사용하지 않는 경우 false로 설정
    });
