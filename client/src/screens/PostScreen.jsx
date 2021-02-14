import React from 'react';
import Select from 'react-select';
import { Text, View, Button, StyleSheet, TextInput, ScrollView } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import Theme from '../Theme';
import HeaderOptions from '../HeaderOptions';
import Spacing from '../components/SpacingComponent';

const styles = StyleSheet.create(Theme);

const mockData = [
  {
    id: '1',
    title: '공지사항',
    link: '',
  },
  {
    id: '2',
    title: '소프트웨어학부',
    link: '',
  },
];

class PostScreen extends React.PureComponent {
  state = {
    selctedOption: null,
  };
  render() {
    const options = mockData.map((item) => {
      return { value: item.id, label: item.title };
    });
    return (
      <ScrollView style={styles.postContainer}>
        <Select options={options} style={styles.postSelect} placeholder="카테고리를 선택해주세요" />
        <Spacing spacing={1} />
        <View style={styles.postSubContainer}>
          <input type="file" style={{ flex: 1 }} />
          <Text style={styles.postLabel}>대표이미지를 골라주세요. 이미지는 png나 jpg 형식만 지원됩니다.</Text>
        </View>
        <Spacing spacing={1} />
        <TextInput style={styles.postInput} placeholder={'포스팅할 내용을 입력해주세요...'} multiline={true} />
        <Spacing spacing={1} />
        <Button style={styles.postButton} title={'저장하기'}></Button>
        <Spacing spacing={1} />
      </ScrollView>
    );
  }
}

const PostStack = createStackNavigator();

export default function PostStackScreen() {
  return (
    <PostStack.Navigator initialRouteName={'Post'}>
      <PostStack.Screen name="Post" component={PostScreen} options={HeaderOptions} />
    </PostStack.Navigator>
  );
}
