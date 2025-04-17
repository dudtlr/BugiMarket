import * as React from "react";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useCallback, useEffect, useState, useRef } from "react";
import {
  SafeAreaView,
  StyleSheet,
  Text,
  View,
  FlatList,
  Dimensions,
  RefreshControl,
  Platform,
  Image,
  Pressable,
  PixelRatio,
  LayoutAnimation,
  TouchableOpacity,
  LayoutChangeEvent,
  Animated
} from "react-native";
import { useIsFocused } from "@react-navigation/native";
import Axios from "axios";
import ItemList from "./ItemList";
import writeIcon from "../../assets/design/pen1.png";
import useStore from "../../../store";
import BottomTabs from "../../components/BottomTabs";
import IonIcon from "react-native-vector-icons/Ionicons";
import MatIcon from "react-native-vector-icons/MaterialIcons";
import OctIcon from "react-native-vector-icons/Octicons";
import BouncyCheckbox from "react-native-bouncy-checkbox";
import { Post } from "../../types/PostType";
import { useSafeAreaInsets } from "react-native-safe-area-context";

type RootStackParamList = {
  List: undefined;
};

type ListScreenProps = NativeStackScreenProps<RootStackParamList, "List">;

const vw = Dimensions.get("window").width;
const vh = Dimensions.get("window").height;

function ListScreen({ route, navigation }: ListScreenProps) {
  const moment = require("moment");
  const insets = useSafeAreaInsets();
  const [currentPage, setCurrentPage] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const { session, url, rangeValue, setRangeValue } = useStore();
  const [isRefreshing, setIsRefreshing] = useState(false);
  const isFocused = useIsFocused();
  const [newPosts, setNewPosts] = useState<Array<Post>>([]);

  /** 게시글 정렬 기준을 체크하기 위한 state 목록 */
  const [isNewChecked, setIsNewChecked] = useState(true);
  const [isOldChecked, setIsOldChecked] = useState(false);
  const [isMuchChecked, setIsMuchChecked] = useState(false);
  const [isLittleChecked, setIsLittleChecked] = useState(false);
  const [isHighChecked, setIsHighChecked] = useState(false);
  const [isLowChecked, setIsLowChecked] = useState(false);

  const [previousChecked, setPreviousChecked] = useState("new");
  const [currentChecked, setCurrentChecked] = useState("new");

  const [modalTop, setModalTop] = useState(-vh / 2.3);
  const [modalBackOpacity, setModalBackOpacity] = useState(0.0);
  const [modalBackZIndex, setModalBackZIndex] = useState(-50);

  const [rangeModalTop, setRangeModalTop] = useState(-vh / 2.3);
  const [rangeBackOpacity, setRangeBackOpacity] = useState(0.0);
  const [rangeBackZIndex, setRangeBackZIndex] = useState(-50);
  const [rangeWidth, setRangeWidth] = useState(0);

  /** 게시글 정렬 모달 open 애니메이션 함수 */
  const modalOpenAnimation = () => {
    LayoutAnimation.configureNext({
      duration: 150,
      update: {
        type: LayoutAnimation.Types.linear
      }
    });
    setModalTop(0);
  };

  /** 게시글 정렬 모달 open 함수 */
  const filterModalOpen = () => {
    setModalBackOpacity(0.2);
    setModalBackZIndex(50);
    modalOpenAnimation();
  };

  /** 게시글 정렬 모달 close 애니메이션 함수 */
  const modalCloseAnimation = () => {
    LayoutAnimation.configureNext({
      duration: 150,
      update: {
        type: LayoutAnimation.Types.linear
      }
    });
    setModalTop(-vh / 2.3);
  };

  /** 게시글 정렬 모달 close 함수 */
  const filterModalClose = () => {
    setModalBackOpacity(0);
    setModalBackZIndex(-50);
    modalCloseAnimation();
  };

  const newHandle = () => {
    setCurrentChecked("new");
  };

  const oldHandle = () => {
    setCurrentChecked("old");
  };

  const muchHandle = () => {
    setCurrentChecked("much");
  };

  const littleHandle = () => {
    setCurrentChecked("little");
  };

  const highHandle = () => {
    setCurrentChecked("high");
  };

  const lowHandle = () => {
    setCurrentChecked("low");
  };

  /** 체크한 정렬 기준에 따라 게시글 목록 정렬하는 함수 */
  const adjustFilter = (checked: string) => {
    switch (checked) {
      case "new":
        newPosts.sort((a, b) =>
          moment(b.createdDate).diff(moment(a.createdDate))
        );
        setPreviousChecked("new");
        break;
      case "old":
        newPosts.sort((a, b) =>
          moment(a.createdDate).diff(moment(b.createdDate))
        );
        setPreviousChecked("old");
        break;
      case "much":
        newPosts.sort((a, b) => b.likes - a.likes);
        setPreviousChecked("much");
        break;
      case "little":
        newPosts.sort((a, b) => a.likes - b.likes);
        setPreviousChecked("little");
        break;
      case "high":
        newPosts.sort((a, b) => b.views - a.views);
        setPreviousChecked("high");
        break;
      case "little":
        newPosts.sort((a, b) => a.views - b.views);
        setPreviousChecked("little");
        break;
      default:
        cancelFilter(previousChecked);
        break;
    }
    filterModalClose();
  };

  /** 게시글 정렬 모달에서 취소 버튼 클릭 함수 */
  const cancelFilter = (checked: string) => {
    switch (checked) {
      case "new":
        newPosts.sort((a, b) =>
          moment(b.createdDate).diff(moment(a.createdDate))
        );
        newHandle();
        break;
      case "old":
        newPosts.sort((a, b) =>
          moment(a.createdDate).diff(moment(b.createdDate))
        );
        oldHandle();
        break;
      case "much":
        newPosts.sort((a, b) => b.likes - a.likes);
        muchHandle();
        break;
      case "little":
        newPosts.sort((a, b) => a.likes - b.likes);
        littleHandle();
        break;
      case "high":
        newPosts.sort((a, b) => b.views - a.views);
        highHandle();
        break;
      case "low":
        newPosts.sort((a, b) => a.views - b.views);
        lowHandle();
        break;
      default:
        newPosts.sort((a, b) =>
          moment(b.createdDate).diff(moment(a.createdDate))
        );
        newHandle();
        break;
    }
    filterModalClose();
  };

  /** currentChecked 가 변경될 때마다 다른 체크박스 해제하고 리렌더링하는 함수 */
  useEffect(() => {
    switch (currentChecked) {
      case "new":
        setIsNewChecked(true);
        setIsOldChecked(false);
        setIsMuchChecked(false);
        setIsLittleChecked(false);
        setIsHighChecked(false);
        setIsLowChecked(false);
        break;
      case "old":
        setIsNewChecked(false);
        setIsOldChecked(true);
        setIsMuchChecked(false);
        setIsLittleChecked(false);
        setIsHighChecked(false);
        setIsLowChecked(false);
        break;
      case "much":
        setIsNewChecked(false);
        setIsOldChecked(false);
        setIsMuchChecked(true);
        setIsLittleChecked(false);
        setIsHighChecked(false);
        setIsLowChecked(false);
        break;
      case "little":
        setIsNewChecked(false);
        setIsOldChecked(false);
        setIsMuchChecked(false);
        setIsLittleChecked(true);
        setIsHighChecked(false);
        setIsLowChecked(false);
        break;
      case "high":
        setIsNewChecked(false);
        setIsOldChecked(false);
        setIsMuchChecked(false);
        setIsLittleChecked(false);
        setIsHighChecked(true);
        setIsLowChecked(false);
        break;
      case "low":
        setIsNewChecked(false);
        setIsOldChecked(false);
        setIsMuchChecked(false);
        setIsLittleChecked(false);
        setIsHighChecked(false);
        setIsLowChecked(true);
        break;
    }
  }, [currentChecked]);

  /** 게시글 작성 화면으로 이동하는 함수 */
  const writePost = useCallback(() => {
    navigation.navigate("Add");
  }, [navigation]);

  /** 서버에서 받아온 게시글 목록을 타일별로 표시하는 함수 */
  const renderItem = (item: Post) => {
    return <ItemList board={item} navigation={navigation} />;
  };

  /** ListScreen 처음 렌더링시 페이징된 데이터를 표시하는 함수 */
  const loadFirstPage = () => {
    console.log("loadFirstPage");
    if(!isLoading) {
      setIsLoading(true);
      setIsRefreshing(true);
      if (rangeValue === 4) {
        Axios.get(`${url}/post/all3?page=0&size=15`)
          .then(res => {
              setNewPosts(res.data);
              newPosts.sort((a, b) =>
              moment(b.createdDate).diff(moment(a.createdDate))
            );
            setIsLoading(false);
            setIsRefreshing(false);
          })
          .catch(err => console.log(err));
      }
      else {
        setIsLoading(true);
        setIsRefreshing(true);
        const searchFilterDto = {
          track: rangeValue === 1 ? rangeText : null,
          departments: rangeValue === 2 ? [rangeText] : null,
          college: rangeValue === 3 ? rangeText : null,
          ys: 1
        };
        Axios.post(`${url}/detailSearchWithPaging`, searchFilterDto)
          .then((res) => {
            setNewPosts(res.data);
            setIsLoading(false);
            setIsRefreshing(false);
          })
          .catch((err) => console.log(err));
      }
    }
  };

  /** 화면이 아래로 내려와 추가적인 게시글을 서버에서 받아왔을 때 추가 렌더링하는 함수 */
  const loadPage = () => {
    if (!isLoading) {
      setIsLoading(true);
      setIsRefreshing(true);
      Axios.get(`${url}/post/all3?page=${currentPage}&size=15`)
        .then((res) => {
          setNewPosts([...newPosts, ...res.data]);
          setCurrentPage(currentPage + 1);
          adjustFilter(currentChecked);
          setIsLoading(false);
          setIsRefreshing(false);
        })
        .catch((err) => console.log(err));
    }
  };

  useEffect(() => {
    if (isFocused) {
      loadFirstPage();
    }
  }, [isFocused]);

  /** 게시글 정렬 모달 컴포넌트 */
  const FilterModal = () => {
    return (
      <View style={filterModalStyles.modalContainer}>
        <View style={{ flex: 1 }}>
          <View
            style={{
              alignItems: "center",
              flex: 1,
              borderBottomWidth: 0.25,
              borderColor: "lightgrey"
            }}
          >
            <Text style={{ fontSize: 20 }}>게시글 정렬</Text>
          </View>
          <View style={filterModalStyles.filterContainer}>
            <View style={filterModalStyles.sectionContainerTop}>
              <Text style={{ flex: 2 }}>작성 일자</Text>
              <View style={{ flex: 8, flexDirection: "row" }}>
                <View
                  style={{
                    flex: 5,
                    flexDirection: "row",
                    alignItems: "center"
                  }}
                >
                  <BouncyCheckbox
                    iconStyle={{ borderRadius: 0 }}
                    innerIconStyle={{ borderRadius: 0 }}
                    fillColor="#3099fc"
                    isChecked={isNewChecked}
                    onPress={newHandle}
                  />
                  <Text style={{ fontSize: 15 }}>최신순</Text>
                </View>
                <View
                  style={{
                    flex: 5,
                    flexDirection: "row",
                    alignItems: "center"
                  }}
                >
                  <BouncyCheckbox
                    iconStyle={{ borderRadius: 0 }}
                    innerIconStyle={{ borderRadius: 0 }}
                    fillColor="#3099fc"
                    isChecked={isOldChecked}
                    onPress={oldHandle}
                  />
                  <Text style={{ fontSize: 15 }}>오래된순</Text>
                </View>
              </View>
            </View>
            <View style={filterModalStyles.sectionContainerMiddle}>
              <Text style={{ flex: 2 }}>관심 등록</Text>
              <View style={{ flex: 8, flexDirection: "row" }}>
                <View
                  style={{
                    flex: 5,
                    flexDirection: "row",
                    alignItems: "center"
                  }}
                >
                  <BouncyCheckbox
                    iconStyle={{ borderRadius: 0 }}
                    innerIconStyle={{ borderRadius: 0 }}
                    fillColor="#3099fc"
                    isChecked={isMuchChecked}
                    onPress={muchHandle}
                  />
                  <Text style={{ fontSize: 15 }}>많은순</Text>
                </View>
                <View
                  style={{
                    flex: 5,
                    flexDirection: "row",
                    alignItems: "center"
                  }}
                >
                  <BouncyCheckbox
                    iconStyle={{ borderRadius: 0 }}
                    innerIconStyle={{ borderRadius: 0 }}
                    fillColor="#3099fc"
                    isChecked={isLittleChecked}
                    onPress={littleHandle}
                  />
                  <Text style={{ fontSize: 15 }}>적은순</Text>
                </View>
              </View>
            </View>
            <View style={filterModalStyles.sectionContainerBottom}>
              <Text style={{ flex: 2 }}>조회수</Text>
              <View style={{ flex: 8, flexDirection: "row" }}>
                <View
                  style={{
                    flex: 5,
                    flexDirection: "row",
                    alignItems: "center"
                  }}
                >
                  <BouncyCheckbox
                    iconStyle={{ borderRadius: 0 }}
                    innerIconStyle={{ borderRadius: 0 }}
                    fillColor="#3099fc"
                    isChecked={isHighChecked}
                    onPress={highHandle}
                  />
                  <Text style={{ fontSize: 15 }}>높은순</Text>
                </View>
                <View
                  style={{
                    flex: 5,
                    flexDirection: "row",
                    alignItems: "center"
                  }}
                >
                  <BouncyCheckbox
                    iconStyle={{ borderRadius: 0 }}
                    innerIconStyle={{ borderRadius: 0 }}
                    fillColor="#3099fc"
                    isChecked={isLowChecked}
                    onPress={lowHandle}
                  />
                  <Text style={{ fontSize: 15 }}>낮은순</Text>
                </View>
              </View>
            </View>
          </View>
          <View style={filterModalStyles.buttonBar}>
            <Pressable
              onPress={() => {
                cancelFilter(previousChecked);
              }}
            >
              <View style={filterModalStyles.cancelButton}>
                <IonIcon name="close" size={25} color="white" />
                <Text style={filterModalStyles.cancelText}>취소</Text>
              </View>
            </Pressable>
            <Pressable
              onPress={() => {
                adjustFilter(currentChecked);
              }}
            >
              <View style={filterModalStyles.applyButton}>
                <IonIcon name="checkmark" size={25} color="white" />
                <Text style={filterModalStyles.applyText}>적용</Text>
              </View>
            </Pressable>
          </View>
        </View>
      </View>
    );
  };

  /** 학부 소속에 따른 게시글 필터링 범위 모달 open animation 함수 */
  const rangeModalOpenAnimation = () => {
    LayoutAnimation.configureNext({
      duration: 150,
      update: {
        type: LayoutAnimation.Types.linear,
        property: LayoutAnimation.Properties.width
      }
    });
    setRangeModalTop(0);
  };

  /** 학부 소속에 따른 게시글 필터링 범위 모달 open 함수 */
  const rangeModalOpen = () => {
    setRangeBackOpacity(0.2);
    setRangeBackZIndex(50);
    rangeModalOpenAnimation();
  };

  /** 학부 소속에 따른 게시글 필터링 범위 모달 close animation 함수 */
  const rangeModalCloseAnimation = () => {
    LayoutAnimation.configureNext({
      duration: 150,
      update: {
        type: LayoutAnimation.Types.linear,
        property: LayoutAnimation.Properties.width
      }
    });
    setRangeModalTop(-vh / 2.3);
  };

  /** 학부 소속에 따른 게시글 필터링 범위 모달 close 함수 */
  const rangeModalClose = () => {
    setRangeBackOpacity(0.0);
    setRangeBackZIndex(-50);
    rangeModalCloseAnimation();
  };

  /** 게시글 필터링 범위 표시하는 원형 버튼 위치 관련 state */
  const [rangeAnimatedValue, setRangeAnimatedValue] = useState(
    new Animated.Value(55)
  );
  /** 게시글 필터링 범위 표시하는 배경선 관련 state */
  const [rangeAnimatedWidth, setRangeAnimatedWidth] = useState(
    new Animated.Value((3 * rangeWidth) / 3.0)
  );
  /** 원형 버튼 위치가 어느 범위인지 표시하는 텍스트 */
  const getRangeCategory = (value) => {
    switch (value) {
      case 1:
        return "트랙";
      case 2:
        return "학부";
      case 3:
        return "단과대";
      case 4:
        return "전체";
    }
  };

  const [rangeCategory, setRangeCategory] = useState(
    getRangeCategory(rangeValue)
  );

  /** 선택한 게시글 범위에 맞춘 본인 학부 텍스트 */
  const getRange = (isFirst: boolean, value: number) => {
    switch (value) {
      case 1:
        return isFirst ? session?.firstTrack : session?.secondTrack;
      case 2:
        return isFirst ? session?.first_department : session?.second_department;
      case 3:
        return isFirst ? session?.first_college : session?.second_college;
      case 4:
        return "한성대학교";
    }
  };
  const [rangeText, setRangeText] = useState(getRangeCategory(rangeValue));

  /** 원형 버튼 이동 애니메이션 */
  const moveAnimation = (index: number) => {
    Animated.timing(rangeAnimatedValue, {
      toValue: 55 + (((index - 1) / 1) * rangeWidth) / 3.0,
      duration: 500,
      useNativeDriver: false
    }).start();
  };

  /** 범위에 따른 막대 채색 범위 이동 애니메이션 */
  const moveWidthAnimation = (index: number) => {
    Animated.timing(rangeAnimatedWidth, {
      toValue: (((index - 1) / 1) * rangeWidth) / 3.0,
      duration: 500,
      useNativeDriver: false
    }).start();
  };

  /** 범위 변경에 따른 전체적인 이동 애니메이션 */
  const moveRange = (index: number) => {
    setRangeValue(index);
    moveAnimation(index);
    moveWidthAnimation(index);
    setRangeText(getRange(currentSelected, index));
    setWhichTrack(currentSelected);
  };

  /** 게시글 범위 필터링 모달에서 적용하기 버튼 클릭시 게시글 목록 리렌더링 함수 */
  const adjustRange = () => {
    setRangeCategory(getRangeCategory(rangeValue));
    if (rangeValue === 4) {
      loadFirstPage();
    } else {
      const searchFilterDto = {
        track: rangeValue === 1 ? rangeText : null,
        departments: rangeValue === 2 ? [rangeText] : null,
        college: rangeValue === 3 ? rangeText : null,
        ys: 1
      };
      Axios.post(`${url}/detailSearchWithPaging`, searchFilterDto)
        .then((res) => {
          setNewPosts(res.data);
        })
        .catch((err) => console.log(err));
    }
    rangeModalClose();
  };
  /** 게시글 범위 필터링 모달에서 초기화 버튼 클릭시 게시글 목록 리렌더링 함수 */
  const resetRange = () => {
    setRangeCategory(getRangeCategory(4));
    setRangeText(getRange(true, 4));
    setWhichTrack(true);
    moveRange(4);
    Axios.get(`${url}/post/all`)
      .then((res) => {
        res.data.sort((a: Post, b: Post) =>
          moment(b.createdDate).diff(moment(a.createdDate))
        );
        setNewPosts(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
    rangeModalClose();
  };
  /** 게시글 범위 필터링 모달 배경 클릭하여 선택하지 않고 모달 닫을 시 함수 */
  const cancelRange = () => {
    switch (rangeCategory) {
      case "트랙":
        setRangeValue(1);
        moveRange(1);
        break;
      case "학부":
        setRangeValue(2);
        moveRange(2);
        break;
      case "단과대":
        setRangeValue(3);
        moveRange(3);
        break;
      case "전체":
        setRangeValue(4);
        moveRange(4);
        break;
    }
    setCurrentSelected(whichTrack);
    rangeModalClose();
  };
  /** currentSelected: 1트랙 검색인지 2트랙 검색인지 => true면 1트랙, false면 2트랙 */
  const [whichTrack, setWhichTrack] = useState(true);
  const [currentSelected, setCurrentSelected] = useState(whichTrack);
  
  /** 게시글 범위 필터링 모달 컴포넌트 */
  const RangeModal = () => {
    return (
      <View style={rangeModalStyles.modalContainer}>
        <View style={{ flex: 1.5 }}>
          <View
            style={{
              alignItems: "center",
              justifyContent: "center",
              flex: 1,
              borderBottomWidth: 0.5,
              borderColor: "lightgrey"
            }}
          >
            <Text style={{ fontSize: 20 }}>게시글 범위 설정</Text>
          </View>
        </View>
        <View
          style={{
            height: vh / 15,
            marginTop: 15,
            flexDirection: "row",
            alignItems: "center",
            justifyContent: "center",
            paddingHorizontal: vw / 10
          }}
        >
          {[1, 2].map((value, index) => (
            <Pressable
              style={value === 1 ? { marginRight: 10 } : { marginLeft: 10 }}
              onPress={() => {
                setCurrentSelected(value === 1);
              }}
              key={index}
            >
              <View
                style={{
                  flexDirection: "row",
                  alignItems: "center",
                  justifyContent: "center"
                }}
              >
                <View
                  style={{
                    width: 30,
                    height: 30,
                    borderRadius: 30,
                    borderWidth: 2,
                    marginRight: 10,
                    borderColor:
                      value === 1
                        ? currentSelected
                          ? "#1e42fe"
                          : "lightgrey"
                        : currentSelected
                        ? "lightgrey"
                        : "#1e42fe",
                    justifyContent: "center",
                    alignItems: "center"
                  }}
                >
                  <View
                    style={{
                      width: 15,
                      height: 15,
                      borderRadius: 15,
                      backgroundColor:
                        value === 1
                          ? currentSelected
                            ? "#1e42fe"
                            : "lightgrey"
                          : currentSelected
                          ? "lightgrey"
                          : "#1e42fe"
                    }}
                  />
                </View>
                <Text>{value} 트랙 검색</Text>
              </View>
            </Pressable>
          ))}
        </View>
        <View
          style={{
            flex: 1.5,
            justifyContent: "center"
          }}
        >
          <View style={{ justifyContent: "center", marginTop: vh / 20 }}>
            <View
              onLayout={(event: LayoutChangeEvent) =>
                setRangeWidth(event.nativeEvent.layout.width)
              }
              style={{
                marginHorizontal: 60,
                width: "auto",
                height: 5,
                borderRadius: 10,
                backgroundColor: "lightgrey"
              }}
            />
            <Animated.View
              style={{
                position: "absolute",
                height: 5,
                left: 60,
                borderRadius: 10,
                width: rangeAnimatedWidth,
                borderColor: rangeValue >= 1 ? "#4a71fd" : "black",
                backgroundColor: rangeValue > 1 ? "#4a71fd" : "transparent"
              }}
            />
            <Animated.View
              style={{
                width: 10,
                height: 10,
                borderRadius: 10,
                position: "absolute",
                left: rangeAnimatedValue,
                backgroundColor: "#1e52fe",
                zIndex: 3,
                borderWidth: 1,
                borderColor: "white"
              }}
            />
            <Pressable
              style={{
                position: "absolute",
                height: 32,
                width: rangeWidth / 6,
                left: 60,
              }}
              onPress={() => moveRange(1)}
            >
              <View />
            </Pressable>
            {[1, 2, 3, 4].map((value, index) => (
              <Pressable
                style={{
                  position: "absolute",
                  height: 19,
                  width: 20,
                  alignItems: "center",
                  left: 50 + ((value - 1) * rangeWidth) / 3
                }}
                onPress={() => moveRange(value)}
                key={index}
              >
                <View
                  style={{
                    height: 19,
                    width: 3.5,
                    borderRadius: 5,
                    backgroundColor: rangeValue >= value ? "#1e52fe" : "grey"
                  }}
                />
              </Pressable>
            ))}
            <Pressable
              style={{
                position: "absolute",
                height: 32,
                width: rangeWidth / 3,
                left: 60 + rangeWidth / 6
              }}
              onPress={() => moveRange(2)}
            >
              <View />
            </Pressable>
            <Pressable
              style={{
                position: "absolute",
                height: 32,
                width: rangeWidth / 3,
                left: 60 + rangeWidth / 2
              }}
              onPress={() => moveRange(3)}
            >
              <View />
            </Pressable>
            <Pressable
              style={{
                position: "absolute",
                height: 32,
                width: rangeWidth / 6,
                left: 60 + (5 * rangeWidth) / 6
              }}
              onPress={() => moveRange(4)}
            >
              <View />
            </Pressable>
          </View>
        </View>
        <View
          style={{
            flex: 3,
            justifyContent: "center",
            alignItems: "center"
          }}
        >
          <Text style={{ fontSize: 23, fontWeight: "bold", color: "#1e52fe" }}>
            {getRange(currentSelected, rangeValue)}
          </Text>
          <Text style={{ fontSize: 18, marginTop: 10 }}>
            내에서 게시글을 찾습니다.
          </Text>
        </View>
        <View style={rangeModalStyles.buttonBar}>
          <Pressable onPress={resetRange}>
            <View style={rangeModalStyles.cancelButton}>
              <IonIcon name="refresh" size={25} color="white" />
              <Text style={rangeModalStyles.cancelText}>초기화</Text>
            </View>
          </Pressable>
          <Pressable onPress={adjustRange}>
            <View style={rangeModalStyles.applyButton}>
              <IonIcon name="checkmark" size={25} color="white" />
              <Text style={rangeModalStyles.applyText}>적용</Text>
            </View>
          </Pressable>
        </View>
      </View>
    );
  };

  return (
    <SafeAreaView style={styles.container}>
      <View
        style={{
          position: "absolute",
          bottom: modalTop,
          borderTopLeftRadius: 30,
          borderTopRightRadius: 30,
          borderTopWidth: 0.34,
          borderLeftWidth: 0.34,
          borderRightWidth: 0.34,
          width: vw,
          height: vh / 2.3,
          zIndex: 55,
          backgroundColor: "white"
        }}
      >
        <FilterModal />
      </View>
      <View
        style={{
          position: "absolute",
          bottom: rangeModalTop,
          borderTopLeftRadius: 30,
          borderTopRightRadius: 30,
          borderTopWidth: 0.34,
          borderLeftWidth: 0.34,
          borderRightWidth: 0.34,
          width: vw,
          height: vh / 2.6,
          zIndex: 55,
          backgroundColor: "white"
        }}
      >
        <RangeModal />
      </View>
      <Pressable
        style={{
          width: vw,
          height: vh,
          position: "absolute",
          bottom: 0,
          opacity: modalBackOpacity,
          backgroundColor: "black",
          zIndex: modalBackZIndex
        }}
        onPress={filterModalClose}
      >
        <View />
      </Pressable>
      <Pressable
        style={{
          width: vw,
          height: vh,
          position: "absolute",
          bottom: 0,
          opacity: rangeBackOpacity,
          backgroundColor: "black",
          zIndex: rangeBackZIndex
        }}
        onPress={cancelRange}
      >
        <View />
      </Pressable>
      <View style={styles.topBar}>
        <View style={styles.topBarLeftSide}>
          <TouchableOpacity onPress={() => rangeModalOpen()}>
            <View
              style={{
                flexDirection: "row",
                alignItems: "center",
                paddingLeft: 15
              }}
            >
              <Text style={{ fontSize: 20, fontWeight: "600" }}>
                {rangeCategory}
              </Text>
              <View style={styles.triangle} />
            </View>
          </TouchableOpacity>
        </View>
        <View style={styles.topBarRightSide}>
          <View style={styles.searchButton}>
            <Pressable
              onPress={() => {
                navigation.navigate("Search");
              }}
            >
              <OctIcon name="search" size={20} style={{ marginRight: 10 }} />
            </Pressable>
          </View>
          <View style={styles.filterButton}>
            <Pressable
              onPress={() => {
                filterModalOpen();
              }}
            >
              <MatIcon name="sort" size={25} style={{ marginLeft: 10 }} />
            </Pressable>
          </View>
        </View>
      </View>
      <View
        style={{
          marginTop: 0,
          height: vh - vh / 11 - vh / 15 - insets.top
        }}
      >
        <FlatList
          data={newPosts}
          keyExtractor={(item, index) => index.toString()}
          renderItem={({ item }: { item: Post }) => renderItem(item)}
          ItemSeparatorComponent={() => <View style={styles.seperator} />}
          refreshControl={
            <RefreshControl onRefresh={loadPage} refreshing={isRefreshing} />
          }
          onEndReached={() => loadPage()}
          onEndReachedThreshold={0.85}
          disableVirtualization={false}
        />
      </View>
      <Pressable style={styles.writeButton} onPress={writePost}>
        <Image source={writeIcon} style={{ width: 60, height: 60 }} />
      </Pressable>
      <BottomTabs navigation={navigation} screen="List" />
    </SafeAreaView>
  );
}

const filterModalStyles = StyleSheet.create({
  background: {
    flex: 1,
    top: -vh / 4,
    height: vh,
    backgroundColor: "#000",
    opacity: 0.5
  },
  modalContainer: {
    backgroundColor: "white",
    borderWidth: 0.34,
    width: vw,
    borderTopLeftRadius: 20,
    borderTopRightRadius: 20,
    height: vh / 2.3,
    position: "absolute",
    paddingTop: 15,
    paddingBottom: vh / 20,
    bottom: 0
  },
  filterContainer: {
    flex: 7,
    paddingHorizontal: vw / 25,
    paddingVertical: vh / 75
  },
  sectionContainerTop: {
    flex: 3,
    borderBottomWidth: 0.5,
    borderColor: "#a7a7a7",
    flexDirection: "row",
    alignItems: "center"
  },
  sectionContainerMiddle: {
    borderBottomWidth: 0.5,
    borderColor: "#a7a7a7",
    flex: 4,
    flexDirection: "row",
    alignItems: "center"
  },
  sectionContainerBottom: {
    flex: 3,
    flexDirection: "row",
    alignItems: "center"
  },
  buttonBar: {
    flex: 2,
    justifyContent: "space-between",
    alignItems: "flex-end",
    flexDirection: "row",
    paddingHorizontal: vw / 10,
    borderTopWidth: 0.25,
    borderColor: "lightgrey"
  },
  cancelButton: {
    flexDirection: "row",
    height: vh / 20,
    width: vw / 3.2,
    borderRadius: 40 / PixelRatio.get(),
    alignItems: "center",
    paddingHorizontal: 15,
    backgroundColor: "#ababab",
    marginLeft: vw / 70
  },
  cancelText: {
    marginLeft: 15,
    fontWeight: "500",
    fontSize: 18,
    color: "white"
  },
  applyButton: {
    width: vw / 3.2,
    height: vh / 20,
    borderRadius: 40 / PixelRatio.get(),
    marginRight: vw / 70,
    flexDirection: "row",
    alignItems: "center",
    paddingHorizontal: 15,
    backgroundColor: "#1e4eff"
  },
  applyText: {
    color: "white",
    marginLeft: 15,
    fontWeight: "500",
    fontSize: 18
  }
});

const rangeModalStyles = StyleSheet.create({
  background: {
    flex: 1,
    top: -vh / 4,
    height: vh,
    backgroundColor: "#000",
    opacity: 0.5
  },
  modalContainer: {
    backgroundColor: "white",
    borderWidth: 0.34,
    width: vw,
    borderTopLeftRadius: 20,
    borderTopRightRadius: 20,
    height: vh / 2.3,
    position: "absolute",
    paddingBottom: vh / 50,
    bottom: 0
  },
  filterContainer: {
    flex: 7,
    paddingHorizontal: vw / 25,
    paddingVertical: vh / 75
  },
  sectionContainerTop: {
    flex: 3,
    borderBottomWidth: 0.5,
    borderColor: "#a7a7a7",
    flexDirection: "row",
    alignItems: "center"
  },
  sectionContainerMiddle: {
    borderBottomWidth: 0.5,
    borderColor: "#a7a7a7",
    flex: 4,
    flexDirection: "row",
    alignItems: "center"
  },
  sectionContainerBottom: {
    flex: 3,
    flexDirection: "row",
    alignItems: "center"
  },
  buttonBar: {
    flex: 1.5,
    justifyContent: "space-between",
    alignItems: "flex-end",
    flexDirection: "row",
    paddingHorizontal: vw / 10,
    borderTopWidth: 0.25,
    borderColor: "lightgrey"
  },
  cancelButton: {
    flexDirection: "row",
    height: vh / 20,
    width: vw / 3.2,
    borderRadius: 40 / PixelRatio.get(),
    alignItems: "center",
    paddingHorizontal: 15,
    backgroundColor: "#ababab",
    marginLeft: vw / 70
  },
  cancelText: {
    marginLeft: 15,
    fontWeight: "500",
    fontSize: 18,
    color: "white"
  },
  applyButton: {
    width: vw / 3.2,
    height: vh / 20,
    borderRadius: 40 / PixelRatio.get(),
    marginRight: vw / 70,
    flexDirection: "row",
    alignItems: "center",
    paddingHorizontal: 15,
    backgroundColor: "#1e4eff"
  },
  applyText: {
    color: "white",
    marginLeft: 15,
    fontWeight: "500",
    fontSize: 18
  }
});

const styles = StyleSheet.create({
  container: {
    height: vh,
    backgroundColor: "white"
  },
  seperator: {
    backgroundColor: "lightgrey",
    opacity: 0.4,
    height: 0.5
  },
  topBar: {
    borderBottomWidth: 0.2,
    height: Platform.OS === "ios" ? vh / 15 : vh / 15,
    flexDirection: "row",
    alignItems: "center"
  },
  topBarLeftSide: {
    flex: 1,
    height: vh / 17.5,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "flex-start",
    paddingLeft: 5
  },
  topBarRightSide: {
    flex: 1,
    height: vh / 17.5,
    flexDirection: "row",
    justifyContent: "flex-end",
    alignItems: "center",
    paddingRight: 5
  },
  filterButton: {
    flexDirection: "row",
    alignItems: "center",
    transform: [{ scaleX: -1 }]
  },
  triangle: {
    width: 0,
    height: 0,
    borderTopWidth: 5,
    borderRightWidth: 3.5,
    borderLeftWidth: 3.5,
    borderTopColor: "#000000",
    borderRightColor: "transparent",
    borderLeftColor: "transparent",
    backgroundColor: "transparent",
    marginLeft: 5
  },
  writeButton: {
    backgroundColor: "#0c61fe",
    position: "absolute",
    zIndex: 30,
    width: 60,
    height: 60,
    borderRadius: 30,
    left: vw / 1.24,
    top: vh / 1.27,
    alignItems: "center",
    justifyContent: "center"
  }
});

export default ListScreen;
