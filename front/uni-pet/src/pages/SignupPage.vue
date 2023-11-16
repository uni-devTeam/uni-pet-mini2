<template>
  <Modal :errorMessage="message"></Modal>
  <TopBackground
    :imageURL="signupBackgroundURL"
    :titleText="signupTitleText"
  ></TopBackground>
  <section class="sign_main_container">
    <h1>회원가입</h1>
    <form @submit.prevent="submitForm">
      <div class="info_container">
        <label class="input_lable_text" for="id">아아디</label>
        <input
          v-model="formData.user_id"
          class="inputTag"
          id="id"
          type="text"
          name="user_id"
        />

        <label class="input_lable_text" for="password">비밀번호</label>
        <input
          v-model="formData.password"
          class="inputTag"
          id="password"
          type="password"
          name="password"
        />

        <label class="input_lable_text" for="rePassword">비밀번호 확인</label>
        <input
          v-model="formData.rePassword"
          class="inputTag"
          id="rePassword"
          type="password"
          name="rePassword"
        />

        <label class="input_lable_text" for="email">이메일</label>
        <input
          v-model="formData.email"
          class="inputTag"
          id="email"
          type="email"
          name="email"
        />

        <label class="input_lable_text" for="name">이름</label>
        <input
          v-model="formData.name"
          class="inputTag"
          id="name"
          type="text"
          name="name"
        />
      </div>

      <div class="info_container">
        <h3>나의 펫 정보</h3>
        <span class="input_lable_text">펫 보유 여부</span>
        <div class="radio_container">
          <input
            v-model="formData.havePet"
            id="havePetTrue"
            type="radio"
            name="havePet"
            value="true"
          />
          <label class="margin90" for="havePetTrue">보유</label>
          <input
            v-model="formData.havePet"
            id="havePetFalse"
            type="radio"
            name="havePet"
            value="false"
            checked="checked"
          />
          <label for="havePetFalse">미보유</label>
        </div>

        <label class="input_lable_text" for="petName">펫 이름 </label>
        <input
          v-model="formData.petName"
          class="inputTag disable_toggle"
          id="petName"
          type="text"
          name="petName"
        />

        <label class="input_lable_text" for="petBirthday">생일 </label>
        <input
          v-model="formData.petBirthday"
          class="inputTag disable_toggle"
          type="datetime-local"
          id="petBirthday"
          name="petBirthday"
        />

        <span class="input_lable_text">암 / 수</span>
        <div class="radio_container">
          <input
            v-model="formData.petGender"
            id="petGenderF"
            type="radio"
            name="petGender"
            value="f"
            class="disable_toggle"
          />
          <label class="margin90" for="petGenderF">여아</label>
          <input
            v-model="formData.petGender"
            id="petGenderW"
            type="radio"
            name="petGender"
            value="m"
            class="disable_toggle"
          />
          <label for="petGenderW">남아</label>
        </div>

        <label class="input_lable_text" for="petType">종류</label>
        <input
          v-model="formData.petType"
          class="inputTag disable_toggle"
          id="petType"
          type="text"
          name="petType"
        />

        <span class="input_lable_text">중성화 여부</span>
        <div class="radio_container">
          <input
            v-model="formData.doNeutering"
            id="doNeuteringTrue"
            type="radio"
            name="doNeutering"
            value="y"
            class="disable_toggle"
          />
          <label class="margin90" for="doNeuteringTrue">⭕</label>
          <input
            v-model="formData.doNeutering"
            id="doNeuteringFalse"
            type="radio"
            name="doNeutering"
            value="n"
            class="disable_toggle"
          />
          <label for="doNeuteringFalse">❌</label>
        </div>

        <label class="input_lable_text" for="petColor">펫 색상</label>
        <input
          v-model="formData.petColor"
          class="inputTag disable_toggle"
          id="petColor"
          type="text"
          name="petColor"
        />

        <label class="input_lable_text" for="petWeight">펫 몸무게</label>
        <input
          v-model="formData.petWeight"
          class="inputTag disable_toggle"
          type="number"
          step="any"
          id="petWeight"
          name="petWeight"
        />

        <label class="input_lable_text" for="petChar">펫 특징</label>
        <textarea
          v-model="formData.petChar"
          class="textarea_tag disable_toggle"
          id="petChar"
          name="petChar"
          cols="30"
          rows="10"
        ></textarea>
        <button class="sign_btn" type="submit">회원가입 전송</button>
      </div>
    </form>
  </section>
</template>

<script setup>
import Modal from "../components/common/Modal.vue";
import TopBackground from "../components/common/TopBackground.vue";
import useHavePet from "../assets/js/havePetSignup";
import signupBackground from "../assets/images/topBackground/Signup_bg.jpg";
import { onMounted, ref } from "vue";

const signupBackgroundURL = signupBackground;
const signupTitleText = "Signup";

onMounted(() => {
  useHavePet();
});

const formData = ref({
  user_id: "",
  password: "",
  rePassword: "",
  email: "",
  name: "",
  havePet: false,
  petName: "",
  petBirthday: "",
  petGender: "",
  petType: "",
  doNeutering: "",
  petColor: "",
  petWeight: "",
  petChar: "",
});

let message = ref("");
const submitForm = () => {
  if (validateForm()) {
    // 폼이 유효한 경우에만 서버로 데이터를 전송
    // useSignup(formData);
  }
};

const validateForm = () => {
  const {
    user_id,
    password,
    rePassword,
    email,
    name,
    havePet,
    petName,
    petBirthday,
    petGender,
    petType,
    doNeutering,
    petColor,
    petWeight,
    petChar,
  } = formData.value;
  if (
    user_id.trim() === "" ||
    password.trim() === "" ||
    rePassword.trim() === "" ||
    email.trim() === "" ||
    name.trim() === ""
  ) {
    message.value = "빈 항목을 작성하세요.";
    window.scrollTo(0, 0);
    return false;
  }
  if (havePet) {
    if (
      petName.trim() === "" ||
      petBirthday.trim() === "" ||
      petGender.trim() === "" ||
      petType.trim() === "" ||
      doNeutering.trim() === "" ||
      petColor.trim() === "" ||
      petWeight.trim() === "" ||
      petChar.trim() === ""
    ) {
      message.value = "펫 항목을 작성하세요.";
      window.scrollTo(0, 0);
      return false;
    }
  }
  return true;
};
</script>

<style scoped>
@import "../assets/css/sign.css";
</style>
