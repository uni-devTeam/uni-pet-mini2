const alertBtn = document.querySelector(".alert_btn");
const alertContainer = document.querySelector(".alert");
const blackBg = document.querySelector(".black_bg");

if(alertBtn !=null && blackBg!= null){
alertBtn.addEventListener("click", () => {
  blackBg.classList.add("display_none");
  alertContainer.classList.add("display_none");
});

blackBg.addEventListener("click", () => {
  blackBg.classList.add("display_none");
  alertContainer.classList.add("display_none");
});
}


    function toggleInputs() {
      var havePetTrue = document.getElementById("havePetTrue");
      var havePetFalse = document.getElementById("havePetFalse");
      var inputFields = document.querySelectorAll(".disable_toggle");

      for (var i = 0; i < inputFields.length; i++) {
        inputFields[i].disabled = havePetFalse.checked;
      }
    }

    // Add an event listener to the radio buttons
    document.getElementById("havePetTrue").addEventListener("change", toggleInputs);
    document.getElementById("havePetFalse").addEventListener("change", toggleInputs);

    // Initial call to set the initial state
    toggleInputs();