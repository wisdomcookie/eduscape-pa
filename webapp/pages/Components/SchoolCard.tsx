import React, { useEffect, useState } from 'react';
import { PieChart } from 'react-minimal-pie-chart';
import OverviewBudget from './StatCard';
import PieChartCard from './PieChartCard';
import GridWithPercentileCircles from './GirdWithPercentiles';
import { render } from 'react-dom';
import ExtraInfo from './ExtraStatsSlides';
import { Options } from './ComparisonScreen';
import Link from 'next/link';
import router from 'next/router';


export interface SchoolInfo {
    name: string;
    dropoutRate: number;
    dropoutRatePercentile: number;
    percentLowIncome: number;
    percentLowIncomePercentile: number;
    collegeBound: number;
    collegeBoundPercentile: number;
    spendingPerStudent: number;
    spendingPerPercentile: number;
    studentToFacultyRatio: number;
    studentToFacultyRatioPercentile: number;
    avgTeacherExperience: number;
    avgTeacherExperiencePercentile: number;
    avgTeacherDegreeLevel: number;
    avgTeacherDegreeLevelPercentile: number;
    avgTeacherSalary: number;
    avgTeacherSalaryPercentile: number;

    percentile: number;
    overall_rating: number;
     // Use strings for education level codes
  }

interface SchoolCardProps {
  schoolInfo: SchoolInfo;
  options: Options;
  year?: Number;
}

const SchoolCard: React.FC<SchoolCardProps> = ({ schoolInfo, options, year }) => {
  const [showStats, setShowStats] = useState(false);
  const [overallScore, setOverallScore] = useState<number>(-1);

  const handleShowStats = () => {
    setShowStats(prevShowStats => !prevShowStats);
    render;
  };
  
  const navigateToSchoolPage = () => {
    const schoolNameParam = encodeURIComponent(schoolInfo.name);
    router.push(`/school/${schoolNameParam}`);
  };
  
  useEffect(() => {
    const fetchOverallScore = async () => {
      try {
        console.log('Fetching school names...');
        const response = year 
          ? await fetch(`http://localhost:8080/schools/overallScore?name=${schoolInfo.name}&year=${year}`)
          : await fetch(`http://localhost:8080/schools/overallScore?name=${schoolInfo.name}`)

        console.log(response)
        if (response.ok) {
          
          const data = await response.json();

          const roundedScore = Math.round(data?.overall_score_average);
          setOverallScore(roundedScore);
        }
        else{
          console.log(response.headers)

        }
      } catch (error) {
        console.error('Error fetching school names:', error);
      }
    };

    fetchOverallScore();
    
  }, []);

  // Outside the component
const getOverallScoreAverage = (jsonString: string | undefined): string => {
  try {
    const parsedData = jsonString ? JSON.parse(jsonString) : null;
    const average = parsedData?.overall_score_average;
    return average !== undefined ? average.toFixed(2) : 'N/A';
  } catch (error) {
    console.error('Error parsing JSON:', error);
    return 'N/A';
  }
};

  

  //overallScore?name=
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
          <h1 style={{ fontSize: '2em', fontWeight: 'bold', minHeight: '110px' }}>{schoolInfo.name}</h1>
          <div style={{ marginTop: '20px' }}>
        <div style={{ fontSize: '1.2em', fontWeight: 'bold' }}>
          Overall Rating: 
        </div>
        <div style={{ backgroundColor: '#ECECEC', borderRadius: '10px', padding: '20px', display: 'inline-block', }}>
  <div style={{ fontSize: '6em', fontWeight: 'bold', color: '#233d4dff', textAlign: 'center' }}>
  {overallScore != -1 && <p>{overallScore}</p> }
  {overallScore == -1 && <p>#</p> }
  </div>
</div>


      </div >
      <div style={{ fontSize: '1.2em', fontWeight: 'bold' , paddingTop: '15px'}}>
          Percentile Rankings:
        </div>
      <div style={{paddingTop: '30px'}}></div>
      <GridWithPercentileCircles
        dropoutRate={options.dropoutRate ? schoolInfo.dropoutRatePercentile : undefined}
        expendituresPerStudent={options.spendingPerStudent ? schoolInfo.spendingPerPercentile : undefined}
        studentToFacultyRatio={options.studentToFacultyRatio ? schoolInfo.studentToFacultyRatioPercentile : undefined}
        avgTeacherEducationLevel={options.avgTeacherDegreeLevel ? schoolInfo.avgTeacherDegreeLevelPercentile : undefined}
        collegeBound={options.collegeBound ? schoolInfo.collegeBoundPercentile : undefined}
        lowIncome={options.percentLowIncome ? schoolInfo.percentLowIncomePercentile : undefined}
        avgTeacherExperience={options.avgTeacherExperience ? schoolInfo.avgTeacherExperiencePercentile : undefined}
        avgTeacherSalary={options.avgTeacherSalary ? schoolInfo.avgTeacherSalaryPercentile : undefined}
      />

{showStats && (
      <div>
        <ExtraInfo schoolInfo={schoolInfo}></ExtraInfo>
        
  <button style = {{padding: '15px 20px', width:'100%', borderRadius: '10px', fontSize: '36px', backgroundColor: '#8ECDDD', color: 'white', cursor: 'pointer', transition: 'background-color 0.3s'}}onClick={() => navigateToSchoolPage()}>More Stats</button>


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