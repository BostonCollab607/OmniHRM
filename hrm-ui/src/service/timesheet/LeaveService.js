// src/services/leaveService.js
import { getLoggedInUser } from '../auth/AuthService';
import api from '../BaseService';
const LEAVE_SERVICE = '/api/LeaveService';

// Function to apply a single leave
export const applyLeave =  (leaveDto) => {
  try {
    leaveDto.userName = getLoggedInUser();
    api.post(LEAVE_SERVICE+'/applyLeave', leaveDto);
  } catch (error) {
    console.error('Error applying leave', error);
    throw error;
  }
};

// Function to apply multiple leaves
export const applyLeaves = (leaveDtoList) => {
  try {
    
    api.post(LEAVE_SERVICE+'/applyLeaves', leaveDtoList);
  } catch (error) {
    console.error('Error applying multiple leaves', error);
    throw error;
  }
};

  // Function to get the remaining leave balance
  export const getLeaveBalance = async () => {
    try {
      let username = getLoggedInUser();
      const response = await api.post(LEAVE_SERVICE+'/getLeaveBalance',{userName: username,  // Using the static username for demonstration
        leaveType: '',
        leaveDuration: '',
        date: []  // Splitting comma-separated dates into an array
        });
      return response.data;
    } catch (error) {
      console.error('Error fetching leave balance', error);
      throw error;
    }
  };

// Function to get used leave dates per type
export const getLeaveDatesPerType = async () => {
  try {
    let username = getLoggedInUser();
    const response = await api.post(LEAVE_SERVICE+'/getLeaveDatesPerType',{userName: username,  // Using the static username for demonstration
      leaveType: '',
      leaveDuration: '',
      date: []  // Splitting comma-separated dates into an array
      });
    return response.data;
  } catch (error) {
    console.error('Error fetching leave dates per type', error);
    throw error;
  }
};
