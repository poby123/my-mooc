import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080';

const getOrganizations = () => {
  return axios.get(`${API_URL}/organization`, {headers: authHeader()});
};

const getOrganization = (organizationId) => {
  return axios.get(`${API_URL}/organization/${organizationId}`, {headers: authHeader()});
};

const getMember = (memberId) => {
  return axios.get(`${API_URL}/member/${memberId}`, {headers: authHeader()});
};

const getCategories = (organizationId) => {
  return axios.get(`${API_URL}/category?organization=${organizationId}`, {headers: authHeader()});
};

const getCategory = (categoryId) => {
  return axios.get(`${API_URL}/category/${categoryId}`, {headers: authHeader()});
};

const getBoards = (categoryId, page, size = 10) => {
  return axios.get(`${API_URL}/board?category=${categoryId}&page=${page}&size=${size}`, {headers: authHeader()});
};

const getBoard = (boardId) => {
  return axios.get(`${API_URL}/board/${boardId}`, {headers: authHeader()});
};

const getComments = (boardId, page, size = 10) => {
  return axios.get(`${API_URL}/comment?board=${boardId}&page=${page}&size=${size}`, {headers: authHeader()});
};

export default {
  getOrganizations,
  getOrganization,
  getMember,
  getCategories,
  getCategory,
  getBoards,
  getBoard,
  getComments
};
