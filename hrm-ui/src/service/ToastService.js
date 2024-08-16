import {toast} from "react-toastify";

const toastConfig = {
    position: "top-right",
    autoClose: 3000,
    hideProgressBar: false,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    progress: undefined,
    theme: "light"
};

export function toastNotify(message){
    toast(message, toastConfig);
}

export function toastInfo(message){
    toast.info(message, toastConfig);
}

export function toastSuccess(message){
    toast.success(message, toastConfig);
}

export function toastError(message){
    toast.error(message, toastConfig);
}

export function toastWarning(message){
    toast.warn(message, toastConfig);
}