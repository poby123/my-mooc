import React from 'react';
import { View, ScrollView, StyleSheet } from 'react-native';
import Theme from '../Theme';
import WriterComponent from './WriterComponent';
import ContentComponent from './ContentComponent';
import FavoriteComponent from './FavoriteComponent';
import CommentComponent from './CommentComponent';

const styles = StyleSheet.create(Theme);

export default class BoardComponent extends React.PureComponent {
  state = { comment: '' };
  constructor(props) {
    super(props);
    this.props = props;
    this.mockData = props.route.params;
    this.handleCommentBox = this.handleCommentBox.bind(this);
    this.handleCommentSubmit = this.handleCommentSubmit.bind(this);
  }

  handleCommentBox(e) {
    this.setState({ comment: e.target.value });
  }

  handleCommentSubmit(e) {
    console.log(e.target.value);
  }

  render() {
    const writer = this.mockData.writer;
    const content = this.mockData.content;
    const Comments = this.mockData.comment.map((comment) => {
      return (
        <View key={comment.id}>
          <WriterComponent image={comment.writer.image} name={comment.writer.name} date={comment.date} />
          <ContentComponent content={comment.content} />
        </View>
      );
    });
    return (
      <React.Fragment>
        <ScrollView style={styles.container}>
          <WriterComponent image={writer.image} name={writer.name} date={content.date} />
          <ContentComponent image={content.image} content={content.content} />
          <FavoriteComponent favoriteNumber={content.favorite} myFavorite={content.myFavorite} />
          <CommentComponent onChange={this.handleCommentBox} onSubmit={this.handleCommentSubmit} />
          <View>{Comments}</View>
        </ScrollView>
      </React.Fragment>
    );
  }
}
