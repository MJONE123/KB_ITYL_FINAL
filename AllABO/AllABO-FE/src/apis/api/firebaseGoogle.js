import { signInWithPopup } from "firebase/auth";
import { auth, provider } from "../utils/firebase";
import axios from "axios";
import { useRouter } from "vue-router"; // Vue Router 사용

export const googleLoginHandler = async () => {
  const router = useRouter(); // Vue Router 인스턴스 사용
  try {
    const result = await signInWithPopup(auth, provider);
    
    // 사용자 정보 받아오기
    const user = result.user;
    const id = user.id;
    console.log("사용자 이메일:", id);
    console.log("사용자 이름:", user.displayName);
    console.log("이메일 확인 여부:", user.emailVerified);

    const idToken = await result.user.getIdToken();
    console.log("ID 토큰:", idToken); // 토큰값 확인
    
    // 이메일 중복 확인 후 처리
    const isRegistered = await checkEmailRegistered(id);

    if (isRegistered) {
      // 이미 회원가입된 이메일이면 메인 페이지로 리디렉션하고 토큰 백엔드 전송
      await sendTokenToBackend(idToken);
      router.push("/");
    } else {
      // 그렇지 않다면 회원가입 페이지로 리디렉션
      router.push("/signup");
    }

  } catch (error) {
    console.error("Google 로그인 실패:", error);
    throw error;
  }
};

// 이메일이 이미 등록된 사용자인지 백엔드에서 확인
const checkEmailRegistered = async (id) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/iddupchk/${id}`
    );
    
    // 백엔드에서 반환된 응답에 따라 처리
    if (response.data === "이미 존재하는 아이디입니다") {
      console.log("이미 사용 중인 이메일입니다.");
      return true; // 이미 가입된 경우
    } else {
      console.log("사용 가능한 이메일입니다.");
      return false; // 가입되지 않은 경우
    }
  } catch (error) {
    console.error("이메일 확인 요청 실패:", error);
    throw error;
  }
};

// ID 토큰을 백엔드로 전송
export const sendTokenToBackend = async (idToken) => {
  try {
    const response = await axios.post(
      `${import.meta.env.VITE_MY_SERVER_URL}/google-login`,
      {
        token: idToken,
      }
    );
    return response.data;
  } catch (error) {
    console.error("백엔드로 토큰 전송 실패:", error);
    throw error;
  }
};
