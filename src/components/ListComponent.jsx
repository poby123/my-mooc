import React from 'react';
import { Text, View, ScrollView, Image, StyleSheet, TouchableHighlight } from 'react-native';
import { IconButton } from '@material-ui/core';
import FavoriteIcon from '@material-ui/icons/Favorite';
import FavoriteBorderIcon from '@material-ui/icons/FavoriteBorder';

const mockData = [
  {
    id: '1',
    writer: {
      name: '뽀로로',
      image: 'https://pds.joins.com/news/component/moneytoday/201110/04/2011100410252859481_1.jpg',
    },
    content: {
      date: '2021.02.10 12:16',
      content:
        'To be able to interact with the screen component, we need to use navigation.setOptions to define our button instead of the options prop. By using navigation.setOptions inside the screen component, we get access to screens props, state, context etc.',
      files: ['file link1, file link2'],
      myLove: true,
      love: 9,
    },
    comment: [
      {
        id: '1',
        writer: {
          name: '크롱',
          image: 'https://file.namu.moe/file/c2790d5985eae82cd1199a4cba2101c131a981542fe03429068587b511c2983b',
        },
        date: '2021.02.11',
        content: '재미없는 뽀로로',
      },
      {
        id: '2',
        writer: {
          name: '크롱',
          image: 'https://file.namu.moe/file/c2790d5985eae82cd1199a4cba2101c131a981542fe03429068587b511c2983b',
        },
        date: '2021.02.11',
        content: '펭귄 뽀로로',
      },
    ],
  },
  {
    id: '2',
    writer: {
      name: '뽀로로',
      image: 'https://pds.joins.com/news/component/moneytoday/201110/04/2011100410252859481_1.jpg',
    },
    content: {
      date: '2021.02.10 12:16',
      content:
        'To be able to interact with the screen component, we need to use navigation.setOptions to define our button instead of the options prop. By using navigation.setOptions inside the screen component, we get access to screens props, state, context etc.',
      files: ['file link1, file link2'],
      myLove: true,
      love: 9,
    },
    comment: [
      {
        id: '1',
        writer: {
          name: '크롱',
          image: 'https://file.namu.moe/file/c2790d5985eae82cd1199a4cba2101c131a981542fe03429068587b511c2983b',
        },
        date: '2021.02.11',
        content: '재미없는 뽀로로',
      },
      {
        id: '2',
        writer: {
          name: '크롱',
          image: 'https://file.namu.moe/file/c2790d5985eae82cd1199a4cba2101c131a981542fe03429068587b511c2983b',
        },
        date: '2021.02.11',
        content: '펭귄 뽀로로',
      },
    ],
  },
  {
    id: '3',
    writer: {
      name: '뽀로로',
      image: 'https://pds.joins.com/news/component/moneytoday/201110/04/2011100410252859481_1.jpg',
    },
    content: {
      date: '2021.02.10 12:16',
      content:
        'To be able to interact with the screen component, we need to use navigation.setOptions to define our button instead of the options prop. By using navigation.setOptions inside the screen component, we get access to screens props, state, context etc.',
      files: ['file link1, file link2'],
      myLove: true,
      love: 9,
    },
    comment: [
      {
        id: '1',
        writer: {
          name: '크롱',
          image: 'https://file.namu.moe/file/c2790d5985eae82cd1199a4cba2101c131a981542fe03429068587b511c2983b',
        },
        date: '2021.02.11',
        content: '재미없는 뽀로로',
      },
      {
        id: '2',
        writer: {
          name: '크롱',
          image: 'https://file.namu.moe/file/c2790d5985eae82cd1199a4cba2101c131a981542fe03429068587b511c2983b',
        },
        date: '2021.02.11',
        content: '펭귄 뽀로로',
      },
    ],
  },
];

const styles = StyleSheet.create({
  container: {
    backgroundColor: 'white',
    marginTop: '0.3em',
    marginLeft: '0.5em',
    marginBottom: '0.3em',
    marginRight: '0.5em',
    paddingTop: '1em',
    fontFamily: 'Nanum Gothic',
  },
  writerContainer: {
    flexDirection: 'row',
  },
  writerSubContainer: {
    flexDirection: 'column',
    justifyContent: 'center',
  },
  profileImage: {
    width: '50px',
    height: '50px',
    borderRadius: '50%',
    marginRight: '1em',
  },
  contentContainer: {
    flexDirection: 'column',
  },
  contentLoveContainer: {
    alignItems: 'center',
    flexDirection: 'row',
    color: 'red',
  },
  loveButton: {
    width: '50px',
  },
});

export default class ListComponent extends React.PureComponent {
  constructor(props) {
    super(props);
    this.props = props;
  }
  componentDidMount() {
    console.log('component is mounted');
  }
  componentWillUnmount() {
    console.log('component did unmounted.');
  }
  render() {
    const ContentList = mockData.map((data) => {
      const writer = data.writer;
      const content = data.content;
      return (
        <TouchableHighlight
          activeOpacity={0.8}
          underlayColor="#DBE8F1"
          onPress={() => {
            console.log(`${data.id} content is pressed`);
            this.props.navigation.push('Board', data);
          }}
          style={styles.container}
          key={data.id}
        >
          <React.Fragment>
            <View style={styles.writerContainer}>
              <Image style={styles.profileImage} source={{ uri: writer.image }} />
              <View style={styles.writerSubContainer}>
                <Text>{writer.name}</Text>
                <Text>{content.date}</Text>
              </View>
            </View>
            <View style={styles.contentContainer}>
              <Text style={{ fontFamily: 'inherit' }}>{content.content}</Text>
              <View style={styles.contentLoveContainer}>
                <IconButton color="inherit" onClick={() => console.log('pressed')}>
                  {content.myLove ? <FavoriteIcon /> : <FavoriteBorderIcon />}
                </IconButton>
                <Text styles={{ color: 'red', width: '20px' }}>{content.love}</Text>
              </View>
            </View>
          </React.Fragment>
        </TouchableHighlight>
      );
    });
    return <ScrollView>{ContentList}</ScrollView>;
  }
}
