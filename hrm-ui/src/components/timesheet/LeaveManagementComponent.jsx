// src/components/LeaveManagementComponent.jsx
import React from 'react';
import LeaveForm from './LeaveFormComponent';
import LeaveDashboard from './LeaveDashBoardComponent';
import './LeaveManagement.css'

const LeaveManagementComponent = () => {
  const username = ''; // Replace with dynamic username if needed

  // A function to refresh the leave dashboard when a new leave is applied
  const refreshLeaveDashboard = () => {
    console.log("Leave dashboard refreshed");
    // You can trigger a refresh here if needed
  };

  return (
    <div>
      <h1>Leave Management System</h1>
      <LeaveForm refreshLeaves={refreshLeaveDashboard} />
      <LeaveDashboard username={username} />
    </div>
  );
};

export default LeaveManagementComponent;
