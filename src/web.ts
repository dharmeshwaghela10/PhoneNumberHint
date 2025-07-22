import { WebPlugin } from '@capacitor/core';

import type { PhoneNumberHintPlugin } from './definitions';

export class PhoneNumberHintWeb extends WebPlugin implements PhoneNumberHintPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
