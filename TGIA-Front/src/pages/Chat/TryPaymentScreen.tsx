import * as React from "react";
import { useState } from "react";
import { Platform } from "react-native";
import useStore from "../../../store";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { WebView } from "react-native-webview";
import SendIntentAndroid from "react-native-send-intent";

type TryPaymentParamList = {
  TryPayment: undefined;
};

type TryPaymentProps = NativeStackScreenProps<
  TryPaymentParamList,
  "TryPayment"
>;

const TryPaymentScreen = ({ route, navigation }: TryPaymentProps) => {
  const [paymentUrl, setPaymentUrl] = useState(route.params?.url);
  /** 송금 성공 여부에 따라 이전 화면을 다르게 렌더링하기 위해 zustand에 저장하기 위한 state */
  const { paymentSuccess, setPaymentSuccess } = useStore();

  /** 송금 시도 완료시 이전 화면으로 돌아가는 함수 */
  const handleNavigationStateChange = (navState) => {
    const { url } = navState;
    console.log(url);
    if (url.includes("payment/success")) {
      setPaymentSuccess(true);
      if (Platform.OS === "android") {
        setPaymentUrl("");
      }
      navigation.goBack();
    } else if (url.includes("payment/fail")) {
      setPaymentSuccess(false);
      if (Platform.OS === "android") {
        setPaymentUrl("");
      }
      navigation.goBack();
    }
  };
  return Platform.OS === "ios" ? (
    <WebView
      source={{ uri: paymentUrl }}
      onNavigationStateChange={handleNavigationStateChange}
      style={{
        position: "absolute",
        display: "none"
      }}
    />
  ) : (
    <WebView
      source={{ uri: paymentUrl }}
      onNavigationStateChange={handleNavigationStateChange}
      onShouldStartLoadWithRequest={(e) => {
        if (e.url.startsWith("intent")) {
          SendIntentAndroid.openAppWithUri(e.url);
          return false;
        }
        return true;
      }}
      originWhitelist={["*"]}
      style={{
        position: "absolute",
        display: "none"
      }}
    />
  );
};

export default TryPaymentScreen;
