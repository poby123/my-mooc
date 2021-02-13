import React from 'react';
import PropTypes from 'prop-types';
import { StyleSheet } from 'react-native';
import Theme from '../Theme';
const styles = StyleSheet.create(Theme);

export default class SelectComponent extends React.PureComponent {
  render() {
    const { data, style } = this.props;
    const optionItems = data.map((item) => {
      return (
        <option key={item.id} value={item.id}>
          {item.title}
        </option>
      );
    });
    console.log(style);
    return <select style={styles.postSelect}>{optionItems}</select>;
  }
}

SelectComponent.propTypes = {
  data: PropTypes.array.isRequired,
};
