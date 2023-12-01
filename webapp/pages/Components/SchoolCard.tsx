import React, { useState } from 'react';
import { PieChart } from 'react-minimal-pie-chart';
import OverviewBudget from './StatCard';
import PieChartCard from './PieChartCard';
import GridWithPercentileCircles from './GirdWithPercentiles';
import { render } from 'react-dom';


export interface SchoolInfo {
    name: string;
    graduationRate: number;
    dropoutRate: number;
    spendingPerStudent: number;
    facultyToStudentRatio: number;
    avgTeacherEducationLevel: string;
    percentile: number;
    overall_rating: number;
    college_bound: number; // Use strings for education level codes
  }

interface SchoolCardProps {
  schoolInfo: SchoolInfo;
}

const SchoolCard: React.FC<SchoolCardProps> = ({ schoolInfo }) => {
  const [showStats, setShowStats] = useState(false);

  const handleShowStats = () => {
    setShowStats(prevShowStats => !prevShowStats);
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
        marginBottom: '20px', // Adjusted maxWidth for two charts side by side
        textAlign: 'center',
        fontPalette: '#22668D',
        transition: 'height 0.5s ease', 
      };
    
      const chartContainerStyle = {
        display: 'flex',
        justifyContent: 'space-between',
        marginBottom: '10px',
      };
    
      const chartStyle = {
        width: '45%', // Each chart takes up 45% of the container
      };
      const infoGridStyle: React.CSSProperties = {
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))',
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
    
      const labelStyle = {
        fontWeight: 'bold',
      };
    
      return (
        <div className="card" style={cardStyle}>
          <h1 style={{ fontSize: '4em', fontWeight: 'bold' }}>{schoolInfo.name}</h1>
          <div style={{ marginTop: '20px' }}>
        <div style={{ fontSize: '1.2em', fontWeight: 'bold' }}>
          Overall Rating:
        </div>
        <div style={{ backgroundColor: '#ECECEC', borderRadius: '10px', padding: '20px', display: 'inline-block', }}>
  <div style={{ fontSize: '6em', fontWeight: 'bold', color: '#233d4dff', textAlign: 'center' }}>
    {schoolInfo.overall_rating}
  </div>
</div>


      </div >
      <div style={{paddingTop: '30px'}}></div>
      <GridWithPercentileCircles
  graduationRate={schoolInfo.graduationRate}
  expendituresPerStudent={schoolInfo.spendingPerStudent}
  studentToFacultyRatio={schoolInfo.facultyToStudentRatio}
  avgTeacherEducationLevel={100}
  collegeBound= {schoolInfo.college_bound}
  lowIncome={0}
/>

{showStats && (
        <div style={infoGridStyle}>
          <PieChartCard
            title="Graduation Rate"
            data={[
              { title: 'Graduation Rate', value: schoolInfo.graduationRate, color: '#36A2EB' },
              { title: 'Remaining', value: 100 - schoolInfo.graduationRate, color: '#f0f0f0' },
            ]}
            percentile={schoolInfo.percentile}
          />

          <OverviewBudget value={"$24,000"} difference={15} percentile={99.6} title={"Expenditures Per Student"} />

          <OverviewBudget value={schoolInfo.facultyToStudentRatio + ":1"} difference={15} percentile={99.6} title={"Student to Faculty Ratio"} />

          <OverviewBudget value={getEducationLevelText(schoolInfo.avgTeacherEducationLevel)} difference={15} percentile={99.6} title={"Avg Teacher Education Level"} />
        </div>
      )}

<div style = {{paddingTop: '10px'}}>
<button onClick={handleShowStats} style={{ backgroundColor: '#ECECEC', border: 'none', padding: '10px 20px', cursor: 'pointer', position: 'relative', borderRadius: '15px 15px 0 0' }}>
  <p style = {{paddingBottom: '12px'}}> {showStats? "Hide Stats" : "Show Stats"}</p>
  <div style={{ position: 'absolute', bottom: '5px', left: '50%', transform: 'translateX(-50%)', width: '30px', height: '1px', background: '#000' }}></div>
  <div style={{ position: 'absolute', bottom: '12px', left: '50%', transform: 'translateX(-50%)', width: '30px', height: '1px', background: '#000' }}></div>
  <div style={{ position: 'absolute', bottom: '19px', left: '50%', transform: 'translateX(-50%)', width: '30px', height: '1px', background: '#000' }}></div>
</button>
</div>
          
        </div>
  );
};

const getEducationLevelText = (educationLevelCode: string) => {
  switch (educationLevelCode) {
    case '1':
      return 'Less than high school graduate';
    case '2':
      return 'High school graduate';
    case '3':
      return 'Some college, less than bachelor\'s degree';
    case '4':
      return 'Bachelor\'s degree (includes master\'s equivalency)';
    case '5':
      return 'Master\'s degree';
    case '6':
      return 'Doctor\'s degree';
    default:
      return 'Unknown';
  }
};

export default SchoolCard;
/*
            <div style={chartContainerStyle}>
              <div style={chartStyle}>
                <h2>Graduation Rate</h2>
                <PieChart
                  data={[
                    { title: 'Graduation Rate', value: schoolInfo.graduationRate, color: '#36A2EB' },
                    { title: 'Remaining', value: 100 - schoolInfo.graduationRate, color: '#f0f0f0' },
                  ]}
                />
              </div>
              <div style={chartStyle}>
                <h2>Dropout Rate</h2>
                <PieChart
                  data={[
                    { title: 'Dropout Rate', value: schoolInfo.dropoutRate, color: '#FFCE56' },
                    { title: 'Remaining', value: 100 - schoolInfo.dropoutRate, color: '#f0f0f0' },
                  ]}
                />
              </div>
            </div>
*/