import React from 'react';
import TitleWithPercentileCircle from './TitleWithPercentageCircle';


interface GridWithPercentileCirclesProps {
  graduationRate: number;
  expendituresPerStudent: number;
  studentToFacultyRatio: number;
  avgTeacherEducationLevel: number;
}

const GridWithPercentileCircles: React.FC<GridWithPercentileCirclesProps> = ({
  graduationRate,
  expendituresPerStudent,
  studentToFacultyRatio,
  avgTeacherEducationLevel,
}) => {
  return (
    <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
      <TitleWithPercentileCircle title="Graduation Rate" percentile={graduationRate} />
<TitleWithPercentileCircle title="Expenditures Per Student" percentile={expendituresPerStudent} />
<TitleWithPercentileCircle title="Student to Faculty Ratio" percentile={studentToFacultyRatio} />
<TitleWithPercentileCircle title="Avg Teacher Education Level" percentile={avgTeacherEducationLevel} />
    </div>
  );
};

export default GridWithPercentileCircles;
