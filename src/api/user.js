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
/**
 * 用户登录
 * @param {*} param0
 * @returns
 */
export const userLoginAPI = ({ username, password }) => {
  const param = new URLSearchParams();
  param.append("username", username);
  param.append("password", password);
  return request({
    url: "/user/login",
    method: "post",
    data: param,
  });
};

/**
 * 获取用户信息
 * @returns
 */
export const getUserInfoAPI = () => {
  return request({
    url: "/user/userInfo",
    method: "get",
  });
};

/**
 * 更新用户信息
 * @param {*} params
 */
export const updateUserInfoAPI = (params) => {
  return request({
    method: "put",
    url: "user/update",
    data: params,
  });
};

/**
 * 跟新用户头像
 * @param {*} params
 * @returns
 */
export const updateUserAvatarAPI = (params) => {
  return request({
    method: "patch",
    url: "/user/updateAvatar",
    params: params,
  });
};
/**
 * 跟新密码
 * @param {*} params 
 * @returns 
 */
export const updateUserPasswordAPI = (params) => {
  return request({
    method: "patch",
    url: "/user/updatePwd",
    data: params,
  });
};
