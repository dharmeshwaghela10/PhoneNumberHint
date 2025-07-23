import { registerPlugin } from '@capacitor/core';

import type { PhoneNumberHintPlugin } from './definitions';

// const PhoneNumberHint = registerPlugin<PhoneNumberHintPlugin>('PhoneNumberHint', {
//   web: () => import('./web').then((m) => new m.PhoneNumberHintWeb()),
// });

const PhoneNumberHint = registerPlugin<PhoneNumberHintPlugin>('PhoneNumberHint',);
export * from './definitions';
export { PhoneNumberHint };
