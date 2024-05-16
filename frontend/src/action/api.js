import axios from "axios";
import { toast } from "react-toastify";
import { getToHome } from "../global/Utility";

window.reLoginTime = null;
window.extraTimeTimeOut = null;

const ax = axios.create({
	baseURL: `http://localhost:9000/`,
	timeout: 30000
});
const apiEndPoints = {
	SIMPLE(url = '') {
		return {
			home: () => ax.get(url),
		}
	},
	AUTH(url = "api/v1/auth") {
		return {
			login: (payload) => ax.post(url + "/login", {...payload.body}, { params: { ...payload.params } }),
			logout: () => ax.delete(url + "/logout"),
			registerNewUser: (body) => {
				const headers = { "Content-Type": "application/json", "accept": "application/json" };
				return ax.post(url + "/register", { ...body }, {
					headers: { ...headers }
				})
			},
			save: (formData) => {
				return ax.post(url + "/save", formData, {
					headers: { "Content-Type": "multipart/form-data" },
					params: {}
				})
			},
			delete: (id, updatedRecord) => ax.put(url + id, updatedRecord),
		}
	}
}

ax.interceptors.response.use(response => {
	return response;
}, function (error) {
	console.log(error);
	if (error?.code === "ERR_NETWORK") {
		toast(`Server not working 🕯️`)
	} else if (error?.response?.status === 400) {
		let text = '';
		for (let key in error.response.data) {
			if (error.response.data.hasOwnProperty(key)) {
				text += `${key} - ${error.response.data[key]}\n`;
			}
		}
		toast.warning(text);
	}else if (error?.response?.status === 401) {
		let text = '';
		for (let key in error.response.data) {
			if (error.response.data.hasOwnProperty(key)) {
				text += `${key} - ${error.response.data[key]}\n`;
			}
		}
		toast.warning(text);
	}
	return Promise.reject(error);
});


export default apiEndPoints;