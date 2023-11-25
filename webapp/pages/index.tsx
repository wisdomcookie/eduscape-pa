
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import RankedSchoolList from './Components/RankedSchoolList';
import SchoolCard from './Components/SchoolCard'
import Header from './Components/Header';
import HomeScreen from './Components/home';



const Home: React.FC = () => {
  const exampleSchoolInfo = {
    name: "Example School",
    graduationRate: 85,
    dropoutRate: 5,
    spendingPerStudent: 10000,
    facultyToStudentRatio: 10,
    avgTeacherEducationLevel: "4",
    percentile: 75,
    overall_rating: 10,
  };

  return (
    <DndProvider backend={HTML5Backend}>
      <div className="Home" style={{backgroundColor: '#ffe9dd'}}>
        <Header></Header>
        <HomeScreen />
      </div>
    </DndProvider>
  );
};

export default Home;