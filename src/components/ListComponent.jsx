import React from 'react';
import { View, StyleSheet, TouchableHighlight, FlatList } from 'react-native';
import WriterComponent from './WriterComponent';
import ContentComponent from './ContentComponent';
import Theme from '../Theme';
import FavoriteComponent from './FavoriteComponent';

const mockData = [
  {
    id: '1',
    writer: {
      name: '뽀로로',
      image: 'https://pds.joins.com/news/component/moneytoday/201110/04/2011100410252859481_1.jpg',
    },
    content: {
      date: '2021.02.10 12:16',
      image: 'https://cdn.pixabay.com/photo/2016/11/29/04/19/ocean-1867285_1280.jpg',
      content:
        'To be able to interact with the screen component, we need to use navigation.setOptions to define our button instead of the options prop. By using navigation.setOptions inside the screen component, we get access to screens props, state, context etc.',
      files: ['file link1, file link2'],
      myFavorite: true,
      favorite: 9,
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
      myFavorite: true,
      favorite: 9,
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
      myFavorite: true,
      favorite: 9,
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

const styles = StyleSheet.create(Theme);

export default class ListComponent extends React.PureComponent {
  constructor(props) {
    super(props);
    if (this.props.route.params) {
      this.category = this.props.route.params;
      this.props.navigation.setOptions({ headerTitle: this.category.title });
    }
  }
  componentDidMount() {
    console.log('component is mounted');
  }
  componentWillUnmount() {
    // this.props.navigation.setOptions({ headerTitle: 'MOOC' });
  }
  render() {
    const renderItem = ({ item }) => (
      <View style={styles.container}>
        <TouchableHighlight
          activeOpacity={0.8}
          underlayColor="#DBE8F1"
          onPress={() => {
            this.props.navigation.push('Board', { data: item, category: this.category });
          }}
        >
          <React.Fragment>
            <WriterComponent image={item.writer.image} name={item.writer.name} date={item.content.date} />
            <ContentComponent image={item.content.image} content={item.content.content} />
          </React.Fragment>
        </TouchableHighlight>
        <FavoriteComponent favoriteNumber={item.content.favorite} myFavorite={item.content.myFavorite} />
      </View>
    );

    return <FlatList data={mockData} renderItem={renderItem} keyExtractor={(item) => item.id} />;
  }
}
