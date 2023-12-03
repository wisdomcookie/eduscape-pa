import React from 'react';
import BarChart from './Components/BarChart';


function test() {
  const xAxisLabels = [

    'Dropout Rate Percentile',

    'Percent Low Income Percentile',

    'College Bound Percentile',

    'Spending Per Student Percentile',

    'Faculty to Student Ratio Percentile',

    'Avg Teacher Experience Percentile',

    'Avg Teacher Degree Level Percentile',

    'Avg Teacher Salary Percentile',
  ];
  
  const yAxisTitle = 'Sales';

  const datasets = [
    {
      label: 'School year A',
      data: [50, 70, 65, 80, 90, 75, 80, 80],
      borderColor: '#3e95cd',
      backgroundColor: '#7bb6dd',
    },
    {
      label: 'School year B',
      data: [30, 45, 60, 70, 80, 95, 80, 80],
      borderColor: '#3cba9f',
      backgroundColor: '#71d1bd',
    },
    // Add more datasets as needed
  ];

  return (
    <div>
      <BarChart xAxisLabels={xAxisLabels} yAxisTitle={yAxisTitle} datasets={datasets} />
    </div>
  );
}

export default test;
