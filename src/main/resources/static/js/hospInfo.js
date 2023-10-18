const hospitalDataListWarpper = document.querySelector(
  ".hospital_dataList_warpper"
);

const API_KEY = config.apikey;
let warpper_Y = 0;
let startGetData = 1;
let endGetData = 15;
const getData = async (startGetData, endGetData) => {
  try {
    const response = await fetch(
      `http://openapi.seoul.go.kr:8088/${API_KEY}/json/LOCALDATA_020301/${startGetData}/${endGetData}/`
    );

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await response.json();

    const dataList = data.LOCALDATA_020301;
    console.log(data);
    if (dataList.RESULT.CODE === "INFO-000") {
      const filtedData = dataList.row
        .map((el) => {
          return {
            address: el.SITEWHLADDR,
            detailedAddress: el.RDNWHLADDR,
            hospitalName: el.BPLCNM,
            phoneNumber: el.SITETEL,
            isOpen: el.TRDSTATENM,
          };
        })
        .filter((el) => {
          return el.isOpen === "영업/정상";
        });
      return filtedData;
    } else {
      return [];
    }
  } catch (error) {
    console.error("Fetch error:", error);
    throw error;
  }
};

window.addEventListener("load", async () => {
  try {
    const data = await getData(startGetData, endGetData);
    addDataToList(data);
  } catch (error) {
    console.error("Error:", error);
  }
});

window.addEventListener("scroll", async () => {
  getContainerY();
  console.log(warpper_Y);
  if (warpper_Y <= window.scrollY) {
    try {
      startGetData = endGetData + 1;
      endGetData += 10;
      const data = await getData(startGetData, endGetData);
      addDataToList(data);
      getContainerY();
    } catch (error) {
      console.error("Error:", error);
    }
  }
});

window.addEventListener("resize", () => {
  getContainerY();
});

const getContainerY = () => {
  warpper_Y =
    hospitalDataListWarpper.getBoundingClientRect().bottom +
    window.pageYOffset -
    800;
};
const addDataToList = (data) => {
  data.forEach((el) => {
    hospitalDataListWarpper.innerHTML += `
        <div class="hospital_dataList_card">
          <img src="/image/hospital_photo.jpg" alt="" />
          <div class="hospital_dataList_text">
            <p class="hospital_dataList_maintext">${el.hospitalName}</p>
            <p>${el.phoneNumber}</p>
            <p>${el.detailedAddress}</p>
            <p class="hospital_dataList_btn">${el.isOpen}</p>
          </div>
        </div>`;
  });
};
