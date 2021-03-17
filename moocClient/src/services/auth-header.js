export default function authHeader() {
  const member = JSON.parse(localStorage.getItem('mooc-member'));

  if (member && member.result) {
    return {'X-ACCESS-TOKEN': member.result};
  } else {
    return {};
  }
}
