import { request } from "@/utils";

/**
 * 注册用户接口
 * @param {*} param0
 * @returns
 */
export const userRegisterAPI = ({ username, password }) => {
  const param = new URLSearchParams();
  param.append("username", username);
  param.append("password", password);

  return request({
    url: "/user/register",
    method: "post",
    data: param,
  });
};

export const userLoginAPI = ({ username, password }) => {
  const param = new URLSearchParams();
  param.append("username", username);
  param.append("password", password);
  return request({
    url: '/user/login',
    method: 'post',
    data:param
  })
};
