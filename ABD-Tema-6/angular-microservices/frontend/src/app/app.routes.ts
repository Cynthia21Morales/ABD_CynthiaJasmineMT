import { Routes } from '@angular/router';

import { Dashboard } from './pages/dashboard/dashboard';
import { Students } from './pages/students/students';
import { Teachers } from './pages/teachers/teachers';
import { Courses } from './pages/courses/courses';

export const routes: Routes = [

  {
    path: '',
    component: Dashboard
  },

  {
    path: 'students',
    component: Students
  },

  {
    path: 'teachers',
    component: Teachers
  },

  {
    path: 'courses',
    component: Courses
  }

];
