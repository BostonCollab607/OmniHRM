import { getLoggedInUser } from "../auth/AuthService";
import api from "../BaseService";

const attendanceUrl = "/api/attendance";
const timesheetURL = "/api/timesheet";

export const logPunchInTime = () => {
    var date = getCurrentDate();
    const attendanceDto = { date };
    api.post(attendanceUrl + "/login", attendanceDto);
}

export const logPunchOutTime = () => {
    var username = getLoggedInUser();
    var date = getCurrentDate();
    const attendanceDto = { username, date };
    api.post(attendanceUrl + "/logout", attendanceDto);
}

export const fetchTasksForDate = (date) => {
    var createdDate = `${date.year}-${date.month}-${date.date}`;
    var username = getLoggedInUser();
    var object = { username, createdDate };
    return api.get(timesheetURL + "/user/date/" + username + "/" + createdDate);
}

export const fetchAttendance = (startDate, endDate) => {
    var username = getLoggedInUser();
    const attendanceDto = { username, startDate, endDate };
    var present = 0;
    return api.post(attendanceUrl + "/range", attendanceDto);
}

export const addTask = (description, durationLogged, createdDate) => {
    var username = getLoggedInUser();
    const task = { description, durationLogged, createdDate };
    return api.post(timesheetURL + "/add", task);
}

export const deleteTask = (taskId) => {
    return api.delete(timesheetURL + "/delete/" + taskId);
}

function getCurrentDate() {
    var d = new Date();
    d = new Date(d.getTime() - d.getTimezoneOffset() * 60000);
    var date = d.toISOString().slice(0, 10);
    return date;
}

export const setEndDay = (globalDate) => {
    const currentDate = new Date(globalDate);
    const nextWeekDate = new Date(globalDate);
    nextWeekDate.setDate(currentDate.getDate() + 7);
    return nextWeekDate;
}

export const getCurrentGlobalDate = () => {
    const currentDate = new Date();
    const startOfWeekDate = new Date(currentDate);
    const dayOfWeek = currentDate.getDay();
    startOfWeekDate.setDate(currentDate.getDate() - dayOfWeek + (dayOfWeek === 0 ? -6 : 1));
    const yyyy = startOfWeekDate.getFullYear();
    const mm = String(startOfWeekDate.getMonth()).padStart(2, '0');
    const dd = String(startOfWeekDate.getDate()).padStart(2, '0');
    return new Date(yyyy, mm, dd);
}

export const startDayOfWorkingWeek = () => {
    var curr = new Date();
    return curr.getDate() - curr.getDay() + 1;
}

export const endDayOfWorkingWeek = () => {
    var curr = new Date();
    return curr.getDate() - curr.getDay() + 5;
}

export const getCurrentMonth = () => {
    var date = new Date();
    return date.getMonth();
}

export const getYear = () => {
    var date = new Date();
    return date.getYear() + 1900;
}

