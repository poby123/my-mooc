import axios from 'axios';

const API_URL = 'http://localhost:8080';

const signup = (id, password) => {
  return axios.post(API_URL + '/signup', {
    id,
    password,
  });
};

const signin = (id, password) => {
  return axios
    .post(API_URL + '/signin', {
      id,
      password,
    })
    .then((response) => {
      if (response.data.result) {
        localStorage.setItem('mooc-member', JSON.stringify(response.data));
      }
      return response.data;
    });
};

const signout = () => {
  localStorage.removeItem('mooc-member');
};

export default {
  signup,
  signin,
  signout,
};
