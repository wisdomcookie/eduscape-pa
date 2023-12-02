import React from 'react';
import TitleWithPercentileCircle from './TitleWithPercentageCircle';

interface GridWithPercentileCirclesProps {
  graduationRate?: number;
  expendituresPerStudent?: number;
  studentToFacultyRatio?: number;
  avgTeacherEducationLevel?: number;
  avgTeacherExperience?: number;
  collegeBound?: number;
  lowIncome?: number;
  facultyDegree?: number;
}

const GridWithPercentileCircles: React.FC<GridWithPercentileCirclesProps> = ({
  graduationRate,
  expendituresPerStudent,
  studentToFacultyRatio,
  avgTeacherEducationLevel,
  collegeBound,
  lowIncome,
  facultyDegree,
  avgTeacherExperience
}) => {
  
  return (
    <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
      {graduationRate !== undefined && <TitleWithPercentileCircle title="Graduation Rate" percentile={graduationRate} />}
      {expendituresPerStudent !== undefined && <TitleWithPercentileCircle title="Expenditures Per Student" percentile={expendituresPerStudent} />}
      {studentToFacultyRatio !== undefined && <TitleWithPercentileCircle title="Student to Faculty Ratio" percentile={studentToFacultyRatio} />}
      {avgTeacherEducationLevel !== undefined && <TitleWithPercentileCircle title="Avg Teacher Education Level" percentile={avgTeacherEducationLevel} />}

      {collegeBound !== undefined && <TitleWithPercentileCircle title="Percent College Bound" percentile={collegeBound} />}
      {lowIncome !== undefined && <TitleWithPercentileCircle title="Percent Low Income" percentile={lowIncome} />}
      {facultyDegree !== undefined && <TitleWithPercentileCircle title="Avg Faculty Degree" percentile={facultyDegree} />}
      {avgTeacherExperience !== undefined && <TitleWithPercentileCircle title="Avg Faculty Experience" percentile={avgTeacherExperience} />}
    </div>
  );
};

export default GridWithPercentileCircles;
