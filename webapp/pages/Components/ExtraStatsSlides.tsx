import React, { useState } from 'react';
import { PieChart } from 'react-minimal-pie-chart';
import OverviewBudget from './StatCard';
import PieChartCard from './PieChartCard';
import GridWithPercentileCircles from './GirdWithPercentiles';
import { render } from 'react-dom';
import { SchoolInfo } from './SchoolCard';

interface SchoolCardProps {
  schoolInfo: SchoolInfo;
}

const ExtraInfo: React.FC<SchoolCardProps> = ({ schoolInfo }) => {
  const [showStats, setShowStats] = useState(false);

  const handleShowStats = () => {
    setShowStats((prevShowStats) => !prevShowStats);
    render;
  };

  const cardStyle: React.CSSProperties = {
    backgroundColor: '#FFFADD',
    color: '#233d4dff',
    paddingTop: '16px',
    paddingLeft: '16px',
    paddingRight: '16px',
    height: 'fit-content',
    borderRadius: '8px',
    boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
    marginBottom: '20px',
    textAlign: 'center',
    fontPalette: '#22668D',
    transition: 'height 0.5s ease',
  };

  const infoGridStyle: React.CSSProperties = {
    display: 'grid',
    gridTemplateColumns: 'repeat(3, 1fr)', // 3 columns
    gap: '20px',
    justifyContent: 'center',
  };

  const circleStyle: React.CSSProperties = {
    width: `${schoolInfo.spendingPerStudent / 50}px`,
    height: `${schoolInfo.spendingPerStudent / 50}px`,
    borderRadius: '50%',
    background: '#fcffebff',
    border: '2px solid #233d4dff',
    margin: 'auto',
    marginBottom: '10px',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    fontWeight: 'bold',
    fontSize: '1.2em',
  };
  const labelStyle: React.CSSProperties = {
    fontWeight: 'bold',
  };

  return (
    <div style={cardStyle}>
      <h1 style={{ fontSize: '2em', fontWeight: 'bold', marginBottom: '10px' }}>Raw Data</h1>

      <div style={infoGridStyle}>
        <div>
          <p><span style={labelStyle}>Average Teacher Degree Level:</span> {schoolInfo?.avgTeacherDegreeLevel !== null && schoolInfo?.avgTeacherDegreeLevel !== -1 ? getEducationLevelText(schoolInfo?.avgTeacherDegreeLevel) : 'unreported'}</p>
          <p><span style={labelStyle}>Average Teacher Experience:</span> {schoolInfo?.avgTeacherExperience !== null && schoolInfo?.avgTeacherExperience !== -1 ?  Math.round(schoolInfo.avgTeacherExperience)  : 'unreported'}</p>
          <p><span style={labelStyle}>Faculty to Student Ratio:</span> {schoolInfo?.facultyToStudentRatio !== null && schoolInfo?.facultyToStudentRatio !== -1 ? Math.round(schoolInfo.facultyToStudentRatio) : 'unreported'}</p>
        </div>

        <div>
          <p><span style={labelStyle}>Percent Drop Out:</span> {schoolInfo?.dropoutRate !== null && schoolInfo?.dropoutRate !== -1 ? Math.round(schoolInfo.dropoutRate*100).toString() + "%" : 'unreported'}</p>
          <p><span style={labelStyle}>Percent Low Income:</span> {schoolInfo?.percentLowIncome !== null && schoolInfo?.percentLowIncome !== -1 ? Math.round(schoolInfo.percentLowIncome*100).toString() + "%" : 'unreported'}</p>
        </div>

        <div>
          <p><span style={labelStyle}>Spending Per Student:</span> {schoolInfo?.spendingPerStudent !== null && schoolInfo?.spendingPerStudent !== -1 ? "$" +Math.round(schoolInfo.spendingPerStudent) : 'unreported'}</p>
          <p><span style={labelStyle}>Percent College Bound:</span> {schoolInfo?.collegeBound !== null && schoolInfo?.collegeBound !== -1 ? Math.round(schoolInfo.collegeBound*100).toString() + "%" : 'unreported'}</p>

        </div>
      </div>
    </div>
  );
};

const getEducationLevelText = (educationLevelCode: number) => {
    console.log(educationLevelCode)
    const roundedCode = Math.round(educationLevelCode);
    console.log(roundedCode)
    switch (roundedCode) {
      case 1:
        return 'Less than high school graduate';
      case 2:
        return 'High school graduate';
      case 3:
        return 'Some college, less than bachelor\'s degree';
      case 4:
        return 'Bachelor\'s degree';
      case 5:
        return 'Master\'s degree';
      case 6:
        return 'Doctor\'s degree';
      default:
        return 'Unknown';
    }
  };
  

export default ExtraInfo;
