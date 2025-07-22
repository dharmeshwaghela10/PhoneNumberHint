import { registerPlugin } from '@capacitor/core';

import type { PhoneNumberHintPlugin } from './definitions';

const PhoneNumberHint = registerPlugin<PhoneNumberHintPlugin>('PhoneNumberHint', {
  web: () => import('./web').then((m) => new m.PhoneNumberHintWeb()),
});

export * from './definitions';
export { PhoneNumberHint };
