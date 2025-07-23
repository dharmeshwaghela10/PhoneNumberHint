# phonenumberhint

this is the custom plugin for getting phone number through the phone number api of google.

## Install

```bash
npm install phonenumberhint
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`getPhoneNumber()`](#getphonenumber)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### getPhoneNumber()

```typescript
getPhoneNumber() => Promise<{ phoneNumber: string; }>
```

**Returns:** <code>Promise&lt;{ phoneNumber: string; }&gt;</code>

--------------------

</docgen-api>
